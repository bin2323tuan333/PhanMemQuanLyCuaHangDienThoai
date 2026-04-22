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
    
    if (acc != null && acc.getPassword().equals(password)) {
      return acc;
    }
    return null;
  }
  
  public void register(String user, String pass) {
  
  }
  
  public String changePassword(int accountId, String oldPass, String newPass) {
    if (oldPass == null || newPass == null) return "EMPTY_INPUT";
    if (oldPass.equals(newPass)) {
      return "SAME_AS_OLD_PASSWORD";
    }
    
    AccountRepository accountRepo = new AccountRepository();
    Account acc = accountRepo.getAccountByID(accountId);
    
    if (acc == null) return "ACCOUNT_NOT_FOUND";
    
    if (!acc.getPassword().equals(oldPass)) {
      return "INVALID_OLD_PASSWORD";
    }
    
    acc.setPassword(newPass);
    accountRepo.updateAccount(acc);
    return "OK";
  }
  
  public void logout() {
  
  }
  
}
