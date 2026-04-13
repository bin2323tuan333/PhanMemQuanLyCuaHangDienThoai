package com.example.models;

public class Account {
  private int accountId;
  private String username;
  private String password;
  private Role role;
  private int employeeId;
  
  public Account() {
  }
  
  public Account(int accountId, String username, String password, Role role, int employeeId) {
    this.accountId = accountId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.employeeId = employeeId;
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
  
  public Role getRole() {
    return this.role;
  }
  
  public void setRole(Role role) {
    this.role = role;
  }
  
  public int getEmployeeId() {
    return this.employeeId;
  }
  
  public void setEmployeeId(int role) {
    this.employeeId = employeeId;
  }
  
  @Override
  public String toString() {
    return "Account(id = " + this.accountId + ", username = " + this.username + ", password = " + this.password + ", role = " + this.role.toString() + ")";
  }
}
