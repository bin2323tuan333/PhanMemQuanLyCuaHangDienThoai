package com.example.services;

import com.example.models.Account;
import com.example.repositories.AccountRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    AccountRepository accountRepository = new AccountRepository();
    Account acc = accountRepository.getAccountByUsername(username);
    acc.setPassword(newPass);
    accountRepository.updateAccount(acc);
  }
}
