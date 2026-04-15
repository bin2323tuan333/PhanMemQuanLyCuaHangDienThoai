package com.example.services;

import com.example.models.Account;
import com.example.repositories.AccountRepository;
import com.example.repositories.IAccountRepository;

public class AuthService implements IAuthService {
  public Account login(String username, String password) {
    if (username == null || password == null) {
      System.out.println("Nhap thieu thong tin");
      return null;
    }
    IAccountRepository accountRepo = new AccountRepository();
    Account acc = accountRepo.getAccountByUsername(username);
    return acc;
  }
  
  public void register(String user, String pass) {
  
  }
  
  public void logout() {
  
  }
  
}
