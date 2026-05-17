package com.example.DTO;

import com.example.models.Brand;
import com.example.models.Category;
import com.example.models.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInfo {
  private int productId;
  private String productName;
  private String description;
  private double price;
  private int stock;
  private boolean status;
  
  
  private Brand brand;
  private Category category;
  
  
  public ProductInfo() {
  
  }
  
  public ProductInfo(int productId, String productName, String description, double price, int stock, boolean status, Brand brand, Category category) {
    this.productId = productId;
    this.productName = productName;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.status = status;
    this.brand = brand;
    this.category = category;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.productId = rs.getInt("product_id");
    this.productName = rs.getString("product_name");
    this.description = rs.getString("description");
    this.price = rs.getDouble("price");
    this.stock = rs.getInt("stock");
    this.status = rs.getBoolean("status");
    
    Category category = new Category();
    category.setCategoryId(rs.getInt("category_id"));
    category.setCategoryName(rs.getString("category_name"));
    this.setCategory(category);
    
    Brand brand = new Brand();
    brand.setBrandId(rs.getInt("brand_id"));
    brand.setBrandName(rs.getString("brand_name"));
    this.setBrand(brand);
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
  
  public boolean getStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public Brand getBrand() {
    return brand;
  }
  
  public void setBrand(Brand brand) {
    this.brand = brand;
  }
  
  public Category getCategory() {
    return category;
  }
  
  public void setCategory(Category category) {
    this.category = category;
  }
}
