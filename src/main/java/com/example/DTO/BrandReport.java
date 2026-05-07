package com.example.DTO;

public class BrandReport {
  public String brandName;
  public int total;
  
  public BrandReport() {
  }
  
  public BrandReport(String brandName, int total) {
    this.brandName = brandName;
    this.total = total;
  }
  
  public String getBrandName() {
    return brandName;
  }
  
  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }
  
  public int getTotal() {
    return total;
  }
  
  public void setTotal(int total) {
    this.total = total;
  }
}
