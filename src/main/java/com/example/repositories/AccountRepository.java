package com.example.repositories;

import com.example.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
  public Account getAccountByUsername(String username) {
    String sql = "SELECT * FROM Account WHERE username = ?";
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql, username);
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
  
  public Account getAccountByID(int accId) {
    String sql = "SELECT * FROM Account WHERE account_id = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, accId);
      try (var rs = pstmt.executeQuery()) {
        Account acc = new Account();
        acc.setFromRS(rs);
        return acc;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public List<Account> getAllAccount() {
    List<Account> list = new ArrayList<>();
    String sql = "SELECT * " +
                         "FROM Account";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (var rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Account acc = new Account();
          acc.setFromRS(rs);
          list.add(acc);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  
  public void insert(Account acc) {
    if (acc == null) return;
    String sql = "INSERT INTO Account (username, password, role_id, employee_id) VALUES (?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            acc.getUsername(),
            acc.getPassword(),
            acc.getRoleId(),
            acc.getEmployeeId());
  }
  
  public void update(Account acc) {
    if (acc == null) return;
    String sql = "UPDATE Account SET username = ?, password = ?, role_id = ? WHERE id = ?";
    DBHelper.Instance().executeUpd(sql,
            acc.getUsername(),
            acc.getPassword(),
            acc.getRoleId(),
            acc.getAccountId());
  }
  
  public void delete(int accId) {
    String sql = "DELETE FROM Account WHERE id = ?";
    DBHelper.Instance().executeUpd(sql, accId);
  }
}
