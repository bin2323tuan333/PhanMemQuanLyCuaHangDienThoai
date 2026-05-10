package com.example.DTO;

public class EmployeeStats {
  private String fullName;
  private int productCount;
  
  public EmployeeStats(String fullName, int productCount) {
    this.fullName = fullName;
    this.productCount = productCount;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public int getProductCount() {
    return productCount;
  }
}
