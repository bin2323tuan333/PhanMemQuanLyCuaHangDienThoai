package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Customer {
  private int customerId;
  private String fullName;
  private Date birthday;
  private String address;
  private String phoneNumber;
  
  public Customer() {
  }
  
  public Customer(int customerId, String fullName, Date birthday, String address, String phoneNumber) {
    this.customerId = customerId;
    this.fullName = fullName;
    this.birthday = birthday;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.setCustomerId(rs.getInt("customer_id"));
    this.setFullName(rs.getString("full_name"));
    this.setBirthday(rs.getDate("birthday"));
    this.setAddress(rs.getString("address"));
    this.setPhoneNumber(rs.getString("phone_number"));
  }
  
  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
  
  public int getCustomerId() {
    return customerId;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
  public Date getBirthday() {
    return birthday;
  }
  
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  @Override
  public String toString() {
    return "Customer(customerId = " + this.customerId + ", fullName = " + this.fullName + ", birthday = " + (this.birthday).getDate() + "/" + ((this.birthday).getMonth() + 1) + "/" + ((this.birthday).getYear() + 1900) + ", address = " + this.address + ", phoneNumber = " + this.phoneNumber + ")";
  }
  
}
