package com.example.services;


public class TempLoginService implements ILoginService {
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            System.out.println("Nhap thieu thong tin");
            return false;
        }
        if (username.equals("admin") && password.equals("123")) {
            return true;
        } else {
            return false;
        }
    }
}
