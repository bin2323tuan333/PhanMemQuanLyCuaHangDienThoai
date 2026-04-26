package com.example.repositories;

import com.example.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
  public List<Customer> getAllCustomers() {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * " +
                         "FROM Customer " +
                         "ORDER BY customer_id DESC";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Customer cus = new Customer();
          cus.setFromRS(rs);
          customers.add(cus);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }
  
  public Customer getCustomerById(int id) {
    String sql = "SELECT * " +
                         "FROM Customer " +
                         "WHERE customer_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Customer cus = new Customer();
          cus.setFromRS(rs);
          return cus;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public List<Customer> searchCustomersByName(String keyword) {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * " +
                         "FROM Customer " +
                         "WHERE full_name " +
                         "LIKE ? " +
                         "ORDER BY customer_id DESC";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Customer cus = new Customer();
          cus.setFromRS(rs);
          customers.add(cus);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }
  
  public List<Customer> searchCustomersByPhoneNumber(String keyword) {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * " +
                         "FROM Customer " +
                         "WHERE phone_number " +
                         "LIKE ? " +
                         "ORDER BY customer_id DESC";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Customer cus = new Customer();
          cus.setFromRS(rs);
          customers.add(cus);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }
  
  public void insertCustomer(Customer cus) {
    String sql = "INSERT INTO Customer (full_name, birthday, address, phone_number) VALUES (?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            cus.getFullName(),
            cus.getBirthday(),
            cus.getAddress(),
            cus.getPhoneNumber());
  }
  
  public void updateCustomer(Customer cus) {
    String sql = "UPDATE Customer SET full_name = ?, birthday = ?, address = ?, phone_number = ? WHERE customer_id = ?";
    DBHelper.Instance().executeUpd(sql,
            cus.getFullName(),
            cus.getBirthday(),
            cus.getAddress(),
            cus.getPhoneNumber(),
            cus.getCustomerId());
  }
  
  public void deleteCustomer(int cusId) {
    String sql = "DELETE FROM Customer WHERE customer_id = ?";
    DBHelper.Instance().executeUpd(sql, cusId);
  }
  
  public int getTotalCustomers() {
    String sql = "SELECT COUNT(*) as total " +
                         "FROM Customer";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  public boolean isPhoneNumberExists(String phoneNumber) {
    String sql = "SELECT COUNT(*) as count " +
                         "FROM Customer " +
                         "WHERE phone_number = ?";
    
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, phoneNumber);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) return rs.getInt(1) > 0;
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public Customer getCustomerByPhoneNumber(String phoneNumber) {
    String sql = "SELECT * " +
                         "FROM Customer " +
                         "WHERE phone_number = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, phoneNumber);
      
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Customer cus = new Customer();
          cus.setFromRS(rs);
          return cus;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}