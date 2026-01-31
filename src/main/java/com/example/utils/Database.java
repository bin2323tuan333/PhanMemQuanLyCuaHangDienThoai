package com.example.utils;

import java.sql.DriverManager;
import java.sql.Connection;

public class Database {
    private static String DB_URL = "jdbc:mysql://localhost:3306/quanlycuahangbandienthoai";
    private static String USER = "root";
    private static String PASS = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
