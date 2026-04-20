package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand {
  private int brandId;
  private String brandName;
  
  public Brand() {
  }
  
  public Brand(int brandId, String brandName) {
    this.brandId = brandId;
    this.brandName = brandName;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.setBrandId(rs.getInt("brand_id"));
    this.setBrandName(rs.getString("brand_name"));
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
  
  @Override
  public String toString() {
    return brandName;
  }
}