package com.example.repositories;

import com.example.models.Product;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public List<Product> getAllProducts() throws SQLException {
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
    public Product getProductByID(int id) throws SQLException {
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
    public List<Product> getProductsByBrand(String Brand) throws SQLException {
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
}       return list;
    }
    public List<Product> searchProductsByName(String keyword) throws SQLException {
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
}       return list;
    }
    public List<Product> searchProductsByCategory(String category) throws SQLException {
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
}      return list;
    }
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) throws SQLException {
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
} return list;
    }

 public void addProduct(Product p) throws SQLException {
        String sql = "INSERT INTO Product (product_name, category_id, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBHelper.Instance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getProductName());
            pstmt.setInt(2, p.getCategoryId());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setInt(4, p.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
}
    }
    public void updateProduct(Product p) throws SQLException {
        String sql = "UPDATE Product SET product_name = ?, category_id = ?, price = ?, stock = ? WHERE product_id = ?";
        try (Connection conn = DBHelper.Instance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getProductName());
            pstmt.setInt(2, p.getCategoryId());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setInt(4, p.getStock());
            pstmt.setInt(5, p.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
}
    }
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection conn = DBHelper.Instance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
}
    }


    }

