package com.example.repositories;

import com.example.models.Account;
import com.example.models.Role;
import com.example.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountRepository implements IAccountRepository {
    public Account findByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";
        ResultSet rs = DBHelper.Instance().executeQuery(sql, username);

        try {
            if (rs != null && rs.next()) {
                Account acc = new Account();
                acc.setId(rs.getInt("id"));
                //...

                Connection conn = rs.getStatement().getConnection();
                rs.close();
                conn.close();

                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Account findById() {
        return null;
    }
    public List<Account> getAllAccount() {
//        List<Account> list = new List<Account>[];
        return null;
    }
    public boolean create() {
        return false;
    }
    public boolean updatePassword() {
        return false;
    }
    public boolean delete() {
        return false;
    }
}
