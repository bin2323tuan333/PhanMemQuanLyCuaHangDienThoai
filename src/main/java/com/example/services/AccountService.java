package com.example.services;

import com.example.models.Account;
import com.example.repositories.AccountRepository;

public class AccountService {
  public Account getAccountByID(int id) {
    AccountRepository accountRepository = new AccountRepository();
    Account acc = accountRepository.getAccountByID(id);
    return acc;
  }
  
  public Account getAccountByEmployeeId(int id) {
    AccountRepository accountRepository = new AccountRepository();
    return accountRepository.getAccountByEmployeeId(id);
  }
  
  public void insertAccount(Account acc) {
    AccountRepository accountRepository = new AccountRepository();
    accountRepository.insertAccount(acc);
  }
  
  public void deleteAccount(int id) {
    AccountRepository accountRepository = new AccountRepository();
    accountRepository.deleteAccount(id);
  }
  
  public void changePassword(String username, String newPass) {
    if (username == null || username.isEmpty() || newPass == null) {
      throw new IllegalArgumentException("Username hoặc password không được để trống");
    }
    
    AccountRepository accountRepository = new AccountRepository();
    Account acc = accountRepository.getAccountByUsername(username);
    
    if (acc == null) {
      throw new RuntimeException("Tài khoản '" + username + "' không tồn tại");
    }
    
    acc.setPassword(newPass);
    accountRepository.updateAccount(acc);
  }
  
  public int getCurrentAccountId() {
    AccountRepository accountRepository = new AccountRepository();
    return accountRepository.getCurrentAccountId();
  }
}
