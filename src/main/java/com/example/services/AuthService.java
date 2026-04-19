package com.example.services;

import com.example.models.Account;
import com.example.repositories.AccountRepository;

public class AuthService implements IAuthService {
  public Account login(String username, String password) {
    if (username == null || password == null) {
      System.out.println("Nhap thieu thong tin");
      return null;
    }
    AccountRepository accountRepo = new AccountRepository();
    Account acc = accountRepo.getAccountByUsername(username);
    if (acc == null) return null;
    if (!acc.getPassword().equals(password)) return null;
    return acc;
  }
  
  public void register(String user, String pass) {
  
  }
  
  public void logout() {
    System.out.println("Đã đăng xuất và quay về màn hình đăng nhập.");
  }
  
}
