package com.example.repositories;

import com.example.models.Account;
import com.example.utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
  public Account getAccountByUsername(String username) {
    String sql = "SELECT * FROM Account WHERE username = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, username);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new Account(
                  rs.getInt("account_id"),
                  rs.getString("username"),
                  rs.getString("password"),
                  rs.getInt("role_id"),
                  rs.getInt("employee_id")
          );
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public Account getAccountByID(int accId) {
    String sql = "SELECT * FROM Account WHERE account_id = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, accId);
      
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new Account(
                  rs.getInt("account_id"),
                  rs.getString("username"),
                  rs.getString("password"),
                  rs.getInt("role_id"),
                  rs.getInt("employee_id")
          );
        }
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
          list.add(
                  new Account(
                          rs.getInt("account_id"),
                          rs.getString("username"),
                          rs.getString("password"),
                          rs.getInt("role_id"),
                          rs.getInt("employee_id")
                  )
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  
  public Account getAccountByEmployeeId(int employeeId) {
    String sql = "SELECT * FROM Account WHERE employee_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, employeeId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new Account(
                  rs.getInt("account_id"),
                  rs.getString("username"),
                  rs.getString("password"),
                  rs.getInt("role_id"),
                  rs.getInt("employee_id")
          );
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public void insertAccount(Account acc) {
    if (acc == null) return;
    String sql = "INSERT INTO Account (username, password, role_id, employee_id) VALUES (?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            acc.getUsername(),
            acc.getPassword(),
            acc.getRoleId(),
            acc.getEmployeeId() == 0 ? null : acc.getEmployeeId());
  }
  
  public void updateAccount(Account acc) {
    if (acc == null) return;
    String sql = "UPDATE Account SET username = ?, password = ?, role_id = ? WHERE account_id = ?";
    DBHelper.Instance().executeUpd(sql,
            acc.getUsername(),
            acc.getPassword(),
            acc.getRoleId(),
            acc.getAccountId());
  }
  
  public void deleteAccount(int accId) {
    String sql = "DELETE FROM Account WHERE account_id = ?";
    DBHelper.Instance().executeUpd(sql, accId);
  }
  
  public int getCurrentAccountId() {
    String sql = "SELECT account_id FROM Account ORDER BY account_id DESC LIMIT 1";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      if (rs.next()) {
        return rs.getInt("account_id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }
}
