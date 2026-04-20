package com.example.repositories;

import com.example.models.Bill;
import com.example.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
  public List<Category> getAllCategories() {
    List<Category> list = new ArrayList<>();
    String sql = "SELECT * FROM Category";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Category category = new Category();
          category.setFromRS(rs);
          list.add(category);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return list;
  }
  
  public Category getCategoryByID(int id) {
    String sql = "SELECT * FROM Category WHERE category_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          Category category = new Category();
          category.setFromRS(rs);
          return category;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return null;
  }
  
  public void insertCategory(Category c) {
    if (c == null) return;
    String sql = "INSERT INTO category (category_name) " +
                         "VALUES (?);";
    DBHelper.Instance().executeUpd(sql, c.getCategoryName());
  }
  
  public void updateCategory(Category c) {
    if (c == null) return;
    String sql = "UPDATE category " +
                         "SET category_name = ? " +
                         "WHERE category_id = ?;";
    DBHelper.Instance().executeUpd(sql, c.getCategoryName(), c.getCategoryId());
  }
  
  public void deteleCategory(int id) {
    String sql = "DELETE FROM category " +
                         "WHERE category_id = ?;";
    DBHelper.Instance().executeUpd(sql, id);
  }
  
}
