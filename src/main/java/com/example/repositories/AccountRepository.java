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
    String sql = "SELECT * FROM Account WHERE username = ?";
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql, username);
      System.out.println("Biến rs lúc này: " + rs);
      if (rs != null && rs.next()) {
        System.out.println("Dang tra ve user");
        Account acc = new Account();
        acc.setAccountId(rs.getInt("account_id"));
        acc.setUsername(rs.getString("username"));
        acc.setPassword(rs.getString("password"));
        acc.setRole(rs.getInt("role_id") == 1 ? Role.Employee : Role.Admin);
        acc.setEmployeeId(rs.getInt("employee_id"));
        return acc;
      }
    } catch (SQLException e) {
      System.err.println("Lỗi khi tìm user: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          Connection conn = rs.getStatement().getConnection();
          rs.close();
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
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
