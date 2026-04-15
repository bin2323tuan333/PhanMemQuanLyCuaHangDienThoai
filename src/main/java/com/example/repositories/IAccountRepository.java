package com.example.repositories;

import com.example.models.*;

import java.util.List;

public interface IAccountRepository {
  Account getAccountByUsername(String username);
  
  Account getAccountByID(int id);
  
  List<Account> getAllAccount();
  
  boolean create();
  
  boolean updatePassword();
  
  boolean delete();
}
