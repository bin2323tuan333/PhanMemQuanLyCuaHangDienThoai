package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
  private int accountId;
  private String username;
  private String password;
  private int roleId;
  private Integer employeeId;

  public Account() {
  }

  public Account(int accountId, String username, String password, int roleId, Integer employeeId) {
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
    this.employeeId = (Integer) rs.getObject("employee_id");
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  @Override
  public String toString() {
    return "Account(id = " + accountId +
            ", username = " + username +
            ", password = " + password +
            ", roleId = " + roleId +
            ", employeeId = " + employeeId + ")";
  }
}