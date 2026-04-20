package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
  private int categoryId;
  private String categoryName;
  
  public Category() {
  }
  
  public Category(int categoryId, String categoryName) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.categoryId = rs.getInt("category_id");
    this.categoryName = rs.getString("category_name");
  }
  
  public int getCategoryId() {
    return categoryId;
  }
  
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }
  
  public String getCategoryName() {
    return categoryName;
  }
  
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  
  @Override
  public String toString() {
    return categoryName;
  }
}
