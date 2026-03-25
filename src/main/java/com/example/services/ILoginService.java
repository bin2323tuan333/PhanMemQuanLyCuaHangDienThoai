package com.example.services;

import com.example.DTO.User;

public interface ILoginService {
    boolean login(String username, String password);
    User getCurrentUser();
}
