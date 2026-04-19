package com.example.repositories;

import com.example.models.Bill;
import com.example.utils.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {

    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM Bill ORDER BY invoice_date DESC";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setInvoiceDate(rs.getTimestamp("invoice_date"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setEmployeeId(rs.getInt("employee_id"));
                bill.setCustomerId(rs.getInt("customer_id"));
                bills.add(bill);
            }
        } finally {
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }

        return bills;
    }

    public Bill getBillById(int id) throws SQLException {
        String sql = "SELECT * FROM Bill WHERE bill_id = ?";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setInvoiceDate(rs.getTimestamp("invoice_date"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setEmployeeId(rs.getInt("employee_id"));
                bill.setCustomerId(rs.getInt("customer_id"));
                return bill;
            }
        } finally {
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        }

        return null;
    }
}