package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

public class Employee {
  private int employeeId;
  private String fullName;
  private boolean gender;
  private Date birthday;
  private String address;
  private String phoneNumber;
  private double salary;
  private String status;
  
  private int accountId; // FK -> Account
  
  public Employee() {
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.employeeId = rs.getInt("employee_id");
    this.fullName = rs.getString("employee_name");
    this.address = rs.getString("address");
    this.birthday = rs.getDate("birthday");
    this.gender = rs.getBoolean("gender");
    this.salary = rs.getDouble("salary");
    this.status = rs.getString("status");
    this.phoneNumber = rs.getString("phone_number");
  }
  
  public Employee(int employeeId, String fullName, boolean gender, Date birthday,
                  String address, String phoneNumber, double salary, String status, int accountId) {
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.status = status;
    this.accountId = accountId;
  }
  
  public int getEmployeeId() {
    return employeeId;
  }
  
  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }
  
  public String getFullName() {
    return fullName;
  }
  
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  
  public boolean getGender() {
    return gender;
  }
  
  public void setGender(boolean gender) {
    this.gender = gender;
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
  
  public double getSalary() {
    return salary;
  }
  
  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  public String getStatus() {
    return status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public int getAccountId() {
    return accountId;
  }
  
  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
  
  @Override
  public String toString() {
    return fullName + " (" + phoneNumber + ")";
  }
}