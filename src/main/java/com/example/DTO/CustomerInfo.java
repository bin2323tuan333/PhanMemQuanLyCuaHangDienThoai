package com.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerInfo {
  private int customerId;
  private String customerName;
  private String phone;
  private Date dob;
  private boolean gender;
  private String address;
  
  public CustomerInfo() {
  }
  
  public CustomerInfo(int customerId, String customerName, String phone, Date dob, boolean gender, String address) {
    this.customerId = customerId;
    this.customerName = customerName;
    this.phone = phone;
    this.dob = dob;
    this.gender = gender;
    this.address = address;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.customerId = rs.getInt("customer_id");
    this.customerName = rs.getString("full_name");
    this.gender = rs.getBoolean("gender");
    this.dob = rs.getDate("birthday");
    this.address = rs.getString("address");
    this.phone = rs.getString("phone_number");
  }
  
  public int getCustomerId() {
    return customerId;
  }
  
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  
  public String getCustomerName() {
    return customerName;
  }
  
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public Date getDob() {
    return dob;
  }
  
  public void setDob(Date dob) {
    this.dob = dob;
  }
  
  public boolean isGender() {
    return gender;
  }
  
  public void setGender(boolean gender) {
    this.gender = gender;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
}
