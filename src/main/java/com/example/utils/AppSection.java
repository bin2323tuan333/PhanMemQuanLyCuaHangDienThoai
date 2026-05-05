package com.example.utils;

import com.example.models.Account;

public class AppSection {
  private static AppSection _instance;
  private Account account;
  
  private AppSection() {
  }
  
  public static AppSection Instance() {
    if (_instance == null) {
      _instance = new AppSection();
    }
    return _instance;
  }
  
  public boolean isAdmin() {
    return account.getRoleId() == 2;
  }
  
  public boolean isEmployee() {
    return account.getRoleId() == 1;
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
