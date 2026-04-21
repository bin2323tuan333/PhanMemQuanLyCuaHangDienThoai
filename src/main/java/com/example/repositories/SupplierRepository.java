package com.example.repositories;

import com.example.models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SupplierRepository {
  
  public List<Supplier> getAllSuppliers() {
    List<Supplier> list = new ArrayList<>();
    String sql = "SELECT * FROM Supplier";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Supplier supplier = new Supplier();
          supplier.setFromRS(rs);
          list.add(supplier);
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public Supplier getSupplierByID(int id) {
    String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Supplier supplier = new Supplier();
          supplier.setFromRS(rs);
          return supplier;
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public List<Supplier> getSupplierByName(String name) {
    List<Supplier> list = new ArrayList<>();
    String sql = "SELECT * FROM Supplier WHERE name = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, name);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Supplier supplier = new Supplier();
          supplier.setFromRS(rs);
          list.add(supplier);
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public void insertSupplier(Supplier supplier) {
    String sql = "INSERT INTO supplier (supplier_id, name, address, phone, email) VALUES (?, ?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            supplier.getSupplierId(),
            supplier.getName(),
            supplier.getAddress(),
            supplier.getEmail(),
            
            supplier.getPhone());
  }
  
  public void updateSupplier(Supplier supplier) {
    String sql = "UPDATE Supplier SET name = ?, address = ?, phone = ?, email = ? WHERE supplier_id = ?";
    DBHelper.Instance().executeUpd(sql,
            supplier.getName(),
            supplier.getAddress(),
            supplier.getEmail(),
            supplier.getPhone(),
            supplier.getSupplierId());
  }
  
  public void deleteSupplier(int supplierId) {
    String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
    DBHelper.Instance().executeUpd(sql, supplierId);
  }
  
  
}

