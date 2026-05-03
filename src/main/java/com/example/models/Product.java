package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
  private int productId;
  private String productName;
  private String description;
  private double price;
  private int stock;
  
  private int categoryId; // FK -> Category
  private int brandId;    // FK -> Brand
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.productId = rs.getInt("product_id");
    this.productName = rs.getString("product_name");
    this.description = rs.getString("description");
    this.price = rs.getDouble("price");
    this.stock = rs.getInt("stock");
    this.categoryId = rs.getInt("category_id");
    this.brandId = rs.getInt("brand_id");
  }
  
  public Product() {
  }
  
  public Product(int productId, String productName, String description,
                 double price, int stock, int categoryId, int brandId) {
    this.productId = productId;
    this.productName = productName;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.categoryId = categoryId;
    this.brandId = brandId;
  }
  
  public int getProductId() {
    return productId;
  }
  
  public void setProductId(int productId) {
    this.productId = productId;
  }
  
  public String getProductName() {
    return productName;
  }
  
  public void setProductName(String productName) {
    this.productName = productName;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public int getStock() {
    return stock;
  }
  
  public void setStock(int stock) {
    this.stock = stock;
  }
  
  public int getCategoryId() {
    return categoryId;
  }
  
  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }
  
  public int getBrandId() {
    return brandId;
  }
  
  public void setBrandId(int brandId) {
    this.brandId = brandId;
  }
  
  @Override
  public String toString() {
    return productName;
  }
  
  
}
