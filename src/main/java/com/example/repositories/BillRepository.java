package com.example.repositories;

import com.example.models.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {
  
  public List<Bill> getAllBills() throws SQLException {
    List<Bill> bills = new ArrayList<>();
    String sql = "SELECT * FROM Bill ORDER BY invoice_date DESC";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Bill bill = new Bill();
          bill.setFromRS(rs);
          bills.add(bill);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return bills;
  }
  
  public Bill getBillById(int id) throws SQLException {
    String sql = "SELECT * FROM Bill WHERE bill_id = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        Bill bill = new Bill();
        bill.setFromRS(rs);
        return bill;
      }
      
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    
    return null;
  }
}