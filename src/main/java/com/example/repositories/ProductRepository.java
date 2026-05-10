package com.example.repositories;

import com.example.DTO.BrandReport;
import com.example.DTO.ProductInfo;
import com.example.DTO.ProductReport;
import com.example.models.Product;
import com.example.utils.DBHelper;
import javafx.scene.chart.PieChart;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
  
  public List<PieChart.Data> getProductCategoryRatio() {
    List<PieChart.Data> data = new ArrayList<>();
    String sql = "SELECT c.category_name, SUM(p.stock) as total_stock " +
                         "FROM product p " +
                         "JOIN category c ON p.category_id = c.category_id " +
                         "GROUP BY c.category_id, c.category_name";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        String name = rs.getString("category_name");
        int stock = rs.getInt("total_stock");
        if (stock > 0) {
          data.add(new PieChart.Data(name, stock));
        }
      }
    } catch (SQLException e) {
      System.err.println("Lỗi truy vấn tỉ lệ tồn kho: " + e.getMessage());
    }
    if (data.isEmpty()) {
      data.add(new PieChart.Data("Kho trống", 1));
    }
    return data;
  }
  
  
  public List<ProductReport> getTopProduct() {
    List<ProductReport> list = new ArrayList<>();
    String sql = "SELECT p.product_id, p.product_name, SUM(bd.quantity) AS total_sold\n" +
                         "FROM product p\n" +
                         "JOIN billdetail bd ON p.product_id = bd.product_id\n" +
                         "JOIN bill b ON bd.bill_id = b.bill_id\n" +
                         "WHERE b.invoice_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)\n" +
                         "GROUP BY p.product_id, p.product_name\n" +
                         "ORDER BY total_sold DESC\n" +
                         "LIMIT 5;";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
      
      while (rs.next()) {
        ProductReport report = new ProductReport(
                rs.getString("product_name"),
                rs.getInt("total_sold")
        );
        list.add(report);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Lỗi truy vấn Top sản phẩm", e);
    }
    return list;
  }
  
  public List<BrandReport> getProductCountByBrand() {
    List<BrandReport> list = new ArrayList<>();
    String sql = "SELECT b.brand_name, SUM(p.stock) AS total " +
                         "FROM brand b LEFT JOIN product p ON b.brand_id = p.brand_id " +
                         "GROUP BY b.brand_id, b.brand_name " +
                         "ORDER BY total DESC";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
      
      while (rs.next()) {
        list.add(new BrandReport(
                rs.getString("brand_name"),
                rs.getInt("total")
        ));
      }
    } catch (SQLException e) {
      throw new RuntimeException("Lỗi truy vấn báo cáo Brand", e);
    }
    return list;
  }
  
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

