package com.example.DTO;

public class SystemSetting {
  private int id;
  private String shopName;
  private String shopAddress;
  private String shopPhone;
  private String taxCode;
  
  public SystemSetting() {
  }
  
  public SystemSetting(int id, String shopName, String shopAddress, String shopPhone, String taxCode) {
    this.id = id;
    this.shopName = shopName;
    this.shopAddress = shopAddress;
    this.shopPhone = shopPhone;
    this.taxCode = taxCode;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getShopName() {
    return shopName;
  }
  
  public void setShopName(String shopName) {
    this.shopName = shopName;
  }
  
  public String getShopAddress() {
    return shopAddress;
  }
  
  public void setShopAddress(String shopAddress) {
    this.shopAddress = shopAddress;
  }
  
  public String getShopPhone() {
    return shopPhone;
  }
  
  public void setShopPhone(String shopPhone) {
    this.shopPhone = shopPhone;
  }
  
  public String getTaxCode() {
    return taxCode;
  }
  
  public void setTaxCode(String taxCode) {
    this.taxCode = taxCode;
  }
}
