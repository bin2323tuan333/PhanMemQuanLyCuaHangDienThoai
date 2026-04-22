package com.example.repositories;

import com.example.models.Product;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
  public List<Product> getAllProducts() {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs != null && rs.next()) {
        Product p = new Product();
        p.setFromRS(rs);
        list.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public Product getProductById(int id) {
    String sql = "SELECT * FROM Product WHERE product_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Product p = new Product();
          p.setFromRS(rs);
          return p;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public List<Product> getProductsByBrand(String Brand) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE brand_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, Brand);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Product p = new Product();
          p.setFromRS(rs);
          list.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<Product> getProductsByName(String keyword) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE product_name LIKE ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "%" + keyword + "%");
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Product p = new Product();
          p.setFromRS(rs);
          list.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<Product> getProductsByCategory(String category) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE category_id LIKE ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, "%" + category + "%");
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Product p = new Product();
          p.setFromRS(rs);
          list.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setDouble(1, minPrice);
      pstmt.setDouble(2, maxPrice);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Product p = new Product();
          p.setFromRS(rs);
          list.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public void insertProduct(Product p) {
    String sql = "INSERT INTO Product (product_name, quantity, description, price, stock, category_id, brand_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            p.getProductName(),
            p.getQuantity(),
            p.getDescription(),
            p.getPrice(),
            p.getStock(),
            p.getCategoryId(),
            p.getBrandId());
  }
  
  public void updateProduct(Product p) {
    String sql = "UPDATE Product SET product_name = ?, quantity = ?, description = ?, " +
                         "price = ?, stock = ?, category_id = ?, brand_id = ? WHERE product_id = ?";
    DBHelper.Instance().executeUpd(sql,
            p.getProductName(),
            p.getQuantity(),
            p.getDescription(),
            p.getPrice(),
            p.getStock(),
            p.getCategoryId(),
            p.getBrandId(),
            p.getProductId());
  }
  
  public void deleteProduct(int id) {
    String sql = "DELETE FROM Product WHERE product_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
  
  
}

