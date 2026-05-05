package com.example.repositories;

import com.example.DTO.ProductInfo;
import com.example.models.Product;
import com.example.utils.DBHelper;

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
  
  public List<ProductInfo> searchProduct(String key, int categoryId, int brandId, double minPrice, double maxPrice) {
    List<ProductInfo> list = new ArrayList<>();
    String sql = "SELECT p.*, b.brand_name, c.category_name " +
                         "FROM Product p " +
                         "INNER JOIN Brand b ON p.brand_id = b.brand_id " +
                         "INNER JOIN Category c ON p.category_id = c.category_id " +
                         "WHERE p.product_name LIKE ? " +
                         "AND (? = -1 OR p.category_id = ?) " +
                         "AND (? = -1 OR p.brand_id = ?) " +
                         "AND (? = -1 OR p.price >= ?) " +
                         "AND (? = -1 OR p.price <= ?);";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);) {
      pstmt.setString(1, key != null ? "%" + key + "%" : "%");
      pstmt.setInt(2, categoryId);
      pstmt.setInt(3, categoryId);
      pstmt.setInt(4, brandId);
      pstmt.setInt(5, brandId);
      pstmt.setDouble(6, minPrice);
      pstmt.setDouble(7, minPrice);
      pstmt.setDouble(8, maxPrice);
      pstmt.setDouble(9, maxPrice);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          ProductInfo p = new ProductInfo();
          p.setFromRS(rs);
          list.add(p);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public int getTotalRemainingStock() {
    int totalStock = 0;
    String sql = """
            SELECT SUM(stock)
            FROM product
            """;
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          totalStock = rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      System.out.println("Lỗi lấy tổng tồn kho: " + e.getErrorCode());
      e.printStackTrace();
    }
    return totalStock;
  }
  
  public List<ProductInfo> getAllProductInfos() {
    List<ProductInfo> list = new ArrayList<>();
    String sql = "SELECT p.*, b.brand_name, c.category_name " +
                         "FROM Product p  " +
                         "INNER JOIN Brand b ON p.brand_id = b.brand_id  " +
                         "INNER JOIN Category c ON p.category_id = c.category_id;  ";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs != null && rs.next()) {
        ProductInfo p = new ProductInfo();
        p.setFromRS(rs);
        list.add(p);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public ProductInfo getProductInfoById(int id) {
    String sql = "SELECT p.*, b.brand_name, c.category_name " +
                         "FROM Product p  " +
                         "INNER JOIN Brand b ON p.brand_id = b.brand_id  " +
                         "INNER JOIN Category c ON p.category_id = c.category_id  " +
                         "WHERE p.product_id = ?;";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          ProductInfo p = new ProductInfo();
          p.setFromRS(rs);
          return p;
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
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
    String sql = "INSERT INTO Product (product_name, description, price, stock, category_id, brand_id) VALUES (?, ?, ?, ?, ?, ?);";
    System.out.println(p.toString());
    DBHelper.Instance().executeUpd(sql,
            p.getProductName(),
            p.getDescription(),
            p.getPrice(),
            p.getStock(),
            p.getCategoryId(),
            p.getBrandId());
  }
  
  public void updateProduct(Product p) {
    String sql = "UPDATE Product SET product_name = ?, description = ?, " +
                         "price = ?, stock = ?, category_id = ?, brand_id = ? WHERE product_id = ?";
    DBHelper.Instance().executeUpd(sql,
            p.getProductName(),
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
  
  public void decreaseStock(int productId, int quantity) {
    String sql = "UPDATE Product SET stock = stock - ? WHERE product_id = ?";
    DBHelper.Instance().executeUpd(sql, quantity, productId);
  }
  
  public void increaseStock(int productId, int quantity) {
    String sql = "UPDATE Product SET stock = stock + ? WHERE product_id = ?";
    DBHelper.Instance().executeUpd(sql, quantity, productId);
  }
  
  
}

