package com.example.DTO;

import com.example.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeInfo {
  private int employeeId;
  private String fullName;
  private boolean gender;
  private Date birthday;
  private String address;
  private String phoneNumber;
  private double salary;
  private boolean status;
  private Role role;
  
  public EmployeeInfo() {
  }
  
  public EmployeeInfo(int employeeId, String fullName, boolean gender, Date birthday, String address, String phoneNumber, double salary, boolean status, Role role) {
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.status = status;
    this.role = role;
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
    this.role = new Role();
    this.role.setFromRS(rs);
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
  
  public boolean getStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public Role getRole() {
    return role;
  }
  
  public void setRole(Role role) {
    this.role = role;
  }
  
  
}
