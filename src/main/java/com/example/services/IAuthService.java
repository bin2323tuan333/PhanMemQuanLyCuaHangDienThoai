package com.example.services;

import com.example.models.Account;

public interface IAuthService {
  Account login(String username, String password);
  
  void register(String username, String password);
  
  void logout();
}
