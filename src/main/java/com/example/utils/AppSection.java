package com.example.utils;

import com.example.models.Account;
import com.example.services.RoleService;

public class AppSection {
  private static AppSection _instance;
  private Account account;
  private RoleService roleService;
  
  private AppSection() {
    roleService = new RoleService();
  }
  
  public static AppSection Instance() {
    if (_instance == null) {
      _instance = new AppSection();
    }
    return _instance;
  }
  
  public boolean isAdmin() {
    return roleService.getRoleByID(account.getRoleId()).getRoleName().equals("ADMIN");
  }
  
  public boolean isEmployee() {
    return roleService.getRoleByID(account.getRoleId()).getRoleName().equals("EMPLOYEE");
  }
  
  public Account getAccount() {
    return account;
  }
  
  public void setAccount(Account account) {
    this.account = account;
  }
  
  public void clearSession() {
    this.account = null;
  }
}
