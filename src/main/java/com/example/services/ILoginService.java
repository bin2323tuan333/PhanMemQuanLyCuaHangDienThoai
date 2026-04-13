package com.example.services;

import com.example.DTO.User;
import com.example.models.Account;

public interface ILoginService {
    boolean login(String username, String password);
    User getCurrentUser();
    public Account getAccountByUsername(String username);
}
