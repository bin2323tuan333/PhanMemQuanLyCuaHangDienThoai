package com.example.repositories;

import com.example.models.Account;
import com.example.models.Role;
import com.example.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountRepository implements IAccountRepository {
    public Account findByUsername(String username) {
        String sql = "SELECT * FROM accounts WHERE username = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = Database.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String roleString = rs.getString("role").toUpperCase();
                Role role = Role.valueOf(roleString);
                return new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        role
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public Account findById() {
        return new Account();
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
