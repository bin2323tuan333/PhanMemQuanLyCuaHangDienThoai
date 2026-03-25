package com.example.services;

import com.example.DTO.User;
import com.example.models.Account;
import com.example.repositories.AccountRepository;
import com.example.repositories.IAccountRepository;

public class LoginService implements ILoginService {
    private User currentUser;


    public boolean login(String username, String password) {
        if (username == null || password == null) {
            System.out.println("Nhap thieu thong tin");
            return false;
        }
        IAccountRepository accountRepo = new AccountRepository();
        Account acc = accountRepo.findByUsername(username);
        if (acc == null) {
            System.out.println("khong tim thay user nay");
            return false;
        } else {
            return password.equals(acc.getPassword());
        }
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
