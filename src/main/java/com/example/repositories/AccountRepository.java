package com.example.repositories;

import com.example.models.Account;
import com.example.utils.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountRepository implements IAccountRepository {
  public Account getAccountByUsername(String username) {
    String sql = "SELECT * FROM Account WHERE username = ?";
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql, username);
      System.out.println("Biến rs lúc này: " + rs);
      if (rs != null && rs.next()) {
        Account acc = new Account();
        acc.setAccountId(rs.getInt("account_id"));
        acc.setUsername(rs.getString("username"));
        acc.setPassword(rs.getString("password"));
        acc.setRoleId(rs.getInt("role_id"));
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
  
  public Account getAccountByID(int id) {
    String sql = "SELECT * FROM Account WHERE account_id = ?";
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql, id);
      System.out.println("Biến rs lúc này: " + rs);
      if (rs != null && rs.next()) {
        Account acc = new Account();
        acc.setFromRS(rs);
        return acc;
      }
    } catch (SQLException e) {
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
