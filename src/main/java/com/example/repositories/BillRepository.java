package com.example.repositories;

import com.example.DTO.BillInfo;
import com.example.DTO.RecentBill;
import com.example.models.Bill;
import com.example.utils.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillRepository {
  public List<BillInfo> filterBills(String keyword, Date fromDate, Date toDate) {
    List<BillInfo> bills = new ArrayList<>();
    
    String sql = "SELECT * " +
                         "FROM bill b " +
                         "JOIN customer c ON b.customer_id = c.customer_id " +
                         "JOIN employee e ON b.employee_id = e.employee_id " +
                         "WHERE (c.full_name LIKE ? OR c.phone_number LIKE ?) " +
                         "AND (? IS NULL OR b.invoice_date >= ?) " +
                         "AND (? IS NULL OR b.invoice_date <= ?)";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      String kw = "%" + (keyword == null ? "" : keyword.trim()) + "%";
      pstmt.setString(1, kw);
      pstmt.setString(2, kw);
      
      if (fromDate == null) {
        pstmt.setNull(3, java.sql.Types.DATE);
        pstmt.setNull(4, java.sql.Types.DATE);
      } else {
        pstmt.setDate(3, fromDate);
        pstmt.setDate(4, fromDate);
      }
      
      if (toDate == null) {
        pstmt.setNull(5, java.sql.Types.DATE);
        pstmt.setNull(6, java.sql.Types.DATE);
      } else {
        pstmt.setDate(5, toDate);
        pstmt.setDate(6, toDate);
      }
      
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          BillInfo billInfo = new BillInfo();
          billInfo.setFromRS(rs);
          bills.add(billInfo);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return bills;
  }
  
  public double getRevenueMonth() {
    Double revenue = 0.0;
    String sql = "SELECT SUM(total_amount)\n" +
                         "        FROM bill \n" +
                         "        WHERE YEAR(invoice_date) = YEAR(CURDATE()) \n" +
                         "          AND MONTH(invoice_date) = MONTH(CURDATE())";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          revenue = rs.getDouble(1);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
      e.printStackTrace();
    }
    return revenue;
  }
  
  public int getThisMonthOrders() {
    int count = 0;
    String sql = """
            SELECT COUNT(*)
            FROM bill
            WHERE YEAR(invoice_date) = YEAR(CURDATE())
              AND MONTH(invoice_date) = MONTH(CURDATE())
            """;
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          count = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      System.out.println("Lỗi lấy số đơn hàng tháng này: " + e.getErrorCode());
      e.printStackTrace();
    }
    return count;
  }
  
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
                         "where b.bill_id = ?";
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
        if (rs.next()) {
          Bill bill = new Bill();
          bill.setFromRS(rs);
          return bill;
        }
        
      }
      
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return null;
  }
  
  public int addBill(Bill bill) {
    String sql = "INSERT INTO bill (customer_id, employee_id, invoice_date, total_amount) VALUES (?, ?, NOW(), ?)";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      
      pstmt.setInt(1, bill.getCustomerId());
      pstmt.setInt(2, bill.getEmployeeId());
      pstmt.setDouble(3, bill.getTotalAmount());
      
      int affectedRows = pstmt.executeUpdate();
      
      if (affectedRows > 0) {
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
          if (rs.next()) {
            int generatedId = rs.getInt(1);
            bill.setBillId(generatedId);
            return generatedId;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
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
  
  public double getTotalRevenue() {
    double totalRevenue = 0.0;
    String sql = "SELECT SUM(total_amount) FROM Bill";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          totalRevenue = rs.getDouble(1);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    
    return totalRevenue;
  }
  
  public double getTotalOrders() {
    int totalOrders = 0;
    String sql = "SELECT COUNT(*) FROM Bill";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          totalOrders = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    
    return totalOrders;
  }
}