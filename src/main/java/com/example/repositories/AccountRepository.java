package com.example.repositories;

import com.example.models.Account;
import com.example.utils.DBHelper;

import java.sql.Connection;
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
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql, accId);
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
    List<Account> list = new ArrayList<>();
    String sql = "SELECT * FROM Account";
    ResultSet rs = null;
    
    try {
      rs = DBHelper.Instance().executeQuery(sql);
      if (rs != null && rs.next()) {
        Account acc = new Account();
        acc.setFromRS(rs);
        list.add(acc);
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
    DBHelper.Instance().executeUpd(sql, acc.getUsername(), acc.getPassword(), acc.getRoleId(), acc.getAccountId());
  }
  
  public void delete(int accId) {
    String sql = "DELETE FROM Account WHERE id = ?";
    DBHelper.Instance().executeUpd(sql, accId);
  }
}
