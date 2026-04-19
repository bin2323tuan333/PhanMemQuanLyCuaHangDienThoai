package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
  private int accountId;
  private String username;
  private String password;
  private int roleId;
  private int employeeId;
  
  public Account() {
  }
  
  public Account(int accountId, String username, String password, int role, int employeeId, int roleId) {
    this.accountId = accountId;
    this.username = username;
    this.password = password;
    this.roleId = roleId;
    this.employeeId = employeeId;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.accountId = rs.getInt("account_id");
    this.username = rs.getString("username");
    this.password = rs.getString("password");
    this.roleId = rs.getInt("role_id");
    this.employeeId = rs.getInt("employee_id");
  }
  
  public int getAccountId() {
    return this.accountId;
  }
  
  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public int getRoleId() {
    return this.roleId;
  }
  
  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }
  
  public int getEmployeeId() {
    return this.employeeId;
  }
  
  public void setEmployeeId(int role) {
    this.employeeId = employeeId;
  }
  
  @Override
  public String toString() {
    return "Account(id = " + this.accountId + ", username = " + this.username + ", password = " + this.password + ", role = " + this.roleId + ")";
  }
}
