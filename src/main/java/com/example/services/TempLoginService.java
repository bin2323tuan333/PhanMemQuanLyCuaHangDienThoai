package com.example.services;


import com.example.DTO.User;

public class TempLoginService implements ILoginService {
    private User currentUser;
    public TempLoginService() {
        currentUser = new User("Phạm Quốc Tuấn", "Quản lý");
    }
    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    @Override
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
