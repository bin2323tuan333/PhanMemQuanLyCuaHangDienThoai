package com.example.models;

public class Account {
    public int id;
    public String username;
    public String password;
    public int role;

    public Account() {}
    public Account(int id, String username, String password, int role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public String toString() {
        return "Account(id = " + this.id + ", username = " + this.username + ", password = " + this.password + ", role = " + this.role + ")";
    }
}
