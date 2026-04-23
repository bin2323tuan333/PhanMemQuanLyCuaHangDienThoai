package com.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInfo {
  private int productId;
  private String productName;
  private int quantity;
  private String description;
  private double price;
  private int stock;
  
  private int categoryId; // FK -> Category
  private int brandId;    // FK -> Brand
  
  private String brandName;
  private String supplierName;
  private String categoryName;
  
  
  public ProductInfo() {
  
  }
  
  public ProductInfo(int productId, String productName, int quantity, String description, double price, int stock, int categoryId, int brandId, String brandName, String supplierName, String categoryName) {
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.description = description;
    this.price = price;
    this.stock = stock;
    this.categoryId = categoryId;
    this.brandId = brandId;
    this.brandName = brandName;
    this.supplierName = supplierName;
    this.categoryName = categoryName;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.productId = rs.getInt("product_id");
    this.productName = rs.getString("product_name");
    this.quantity = rs.getInt("quantity");
    this.description = rs.getString("description");
    this.price = rs.getDouble("price");
    this.stock = rs.getInt("stock");
    this.categoryId = rs.getInt("category_id");
    this.brandId = rs.getInt("brand_id");
    this.brandName = rs.getString("brand_name");
    this.supplierName = rs.getString("supplier_name");
    this.categoryName = rs.getString("category_name");
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
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
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
  
  public String getBrandName() {
    return brandName;
  }
  
  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }
  
  public String getCategoryName() {
    return categoryName;
  }
  
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  
  public String getSupplierName() {
    return supplierName;
  }
  
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }
}
