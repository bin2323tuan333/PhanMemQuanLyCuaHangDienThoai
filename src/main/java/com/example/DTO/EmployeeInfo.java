package com.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeInfo {
  private int employeeId;
  private String fullName;
  private boolean gender;
  private Date birthday;
  private String address;
  private String phoneNumber;
  private double salary;
  private boolean status;
  private String roleName;
  
  public EmployeeInfo() {
  }
  
  public EmployeeInfo(int employeeId, String fullName, boolean gender, Date birthday, String address, String phoneNumber, double salary, boolean status, String roleName) {
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.status = status;
    this.roleName = roleName;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.employeeId = rs.getInt("employee_id");
    this.fullName = rs.getString("employee_name");
    this.gender = rs.getBoolean("gender");
    this.birthday = rs.getDate("birthday");
    this.address = rs.getString("address");
    this.phoneNumber = rs.getString("phone_number");
    this.salary = rs.getDouble("salary");
    this.status = rs.getBoolean("status");
    this.roleName = rs.getString("role_name");
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
  
  public boolean isGender() {
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
  
  public boolean getStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public String getRoleName() {
    return roleName;
  }
  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  
}
