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
}
