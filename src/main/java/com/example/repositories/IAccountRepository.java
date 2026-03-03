package com.example.repositories;

import com.example.models.*;

import java.util.List;

public interface IAccountRepository {
    Account findByUsername(String username);
    Account findById();
    List<Account> getAllAccount();
    boolean create();
    boolean updatePassword();
    boolean delete();
}
