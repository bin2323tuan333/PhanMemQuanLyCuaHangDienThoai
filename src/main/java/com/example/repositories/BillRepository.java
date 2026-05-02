package com.example.repositories;

import com.example.DTO.BillInfo;
import com.example.DTO.RecentBill;
import com.example.models.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {
  
  public List<Bill> getAllBills() {
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
  
  public List<BillInfo> getAllBillInfos() {
    List<BillInfo> list = new ArrayList<>();
    String sql = "SELECT * " +
                         "FROM Bill b\n" +
                         "LEFT JOIN Customer c ON b.customer_id = c.customer_id\n" +
                         "LEFT JOIN Employee e ON b.employee_id = e.employee_id\n" +
                         "ORDER BY b.invoice_date DESC;";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          BillInfo bill = new BillInfo();
          bill.setFromRS(rs);
          list.add(bill);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return list;
  }
  
  public BillInfo getBillInfoByID(int id) {
    String sql = "SELECT * " +
                         "FROM Bill b\n" +
                         "LEFT JOIN Customer c ON b.customer_id = c.customer_id " +
                         "LEFT JOIN Employee e ON b.employee_id = e.employee_id " +
                         "where b.bill_id = ?" +
                         " BY b.invoice_date DESC";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          BillInfo bill = new BillInfo();
          bill.setFromRS(rs);
          return bill;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return null;
  }
  
  public Bill getBillById(int id) {
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
  
  public void insertBill(Bill b) {
    if (b == null) return;
    String sql = "INSERT INTO Bill (invoice_date, total_amount, employee_id, customer_id) " +
                         "VALUES (?, ?, ?, ?);";
    DBHelper.Instance().executeUpd(sql,
            b.getInvoiceDate(),
            b.getTotalAmount(),
            b.getEmployeeId(),
            b.getCustomerId());
  }
  
  public void updateBill(Bill b) {
    if (b == null) return;
    String sql = "UPDATE Bill  SET  invoice_date = ?,  total_amount = ?,  employee_id = ?,  customer_id = ? " +
                         "WHERE bill_id = ?;";
    DBHelper.Instance().executeUpd(sql, b.getInvoiceDate(), b.getTotalAmount(), b.getEmployeeId(), b.getCustomerId(), b.getBillId());
  }
  
  public void deleteBill(int id) {
    String sql = "DELETE FROM Bill WHERE bill_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
  
  public List<RecentBill> searchBills(String keyword) {
    List<RecentBill> bills = new ArrayList<>();
    String sql = "SELECT  b.bill_id,  b.invoice_date,  b.total_amount,  c.full_name AS customer_name,  e.employee_name AS employee_name " +
                         "FROM Bill b " +
                         "LEFT JOIN Customer c ON b.customer_id = c.customer_id " +
                         "LEFT JOIN Employee e ON b.employee_id = e.employee_id " +
                         "WHERE  b.bill_id LIKE ? OR c.full_name LIKE ? OR e.employee_name LIKE ? OR b.total_amount LIKE ? " +
                         "ORDER BY b.invoice_date DESC;";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      String searchPattern = "%" + keyword + "%";
      pstmt.setString(1, searchPattern);
      pstmt.setString(2, searchPattern);
      pstmt.setString(3, searchPattern);
      pstmt.setString(4, searchPattern);
      ResultSet rs = pstmt.executeQuery();
      while (rs != null && rs.next()) {
        RecentBill bill = new RecentBill();
        bill.setFromRS(rs);
        bills.add(bill);
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return bills;
    
  }
}