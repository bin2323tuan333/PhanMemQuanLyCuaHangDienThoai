package com.example.utils;

import java.sql.*;

public class DBHelper {
    private static String DB_URL = "jdbc:mysql://localhost:3306/quanlycuahangbandienthoai";
    private static String USER = "root";
    private static String PASS = "";

    private static DBHelper _instance;
    public static DBHelper Instance() {
        if (_instance == null) _instance = new DBHelper();
        return _instance;
    }
    private DBHelper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver MySQL!");
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ResultSet executeQuery(String sql, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            return null;
        }
    }
    public void executeUpd(String sql, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
