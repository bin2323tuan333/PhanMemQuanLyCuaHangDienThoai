package com.example.repositories;

import com.example.DTO.EmployeeInfo;
import com.example.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
  public List<Employee> getAllEmployees() {
    List<Employee> list = new ArrayList<>();
    String sql = "SELECT * FROM Employee ";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Employee e = new Employee();
          e.setFromRS(rs);
          list.add(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    return list;
  }
  
  public List<EmployeeInfo> getAllEmployeeInfo() {
    List<EmployeeInfo> list = new ArrayList<>();
    String sql = "SELECT e.employee_id, e.employee_name, e.gender, e.birthday, e.address, e.phone_number, e.salary, e.status, r.role_name " +
                         "FROM Employee e " +
                         "LEFT JOIN Account a ON e.employee_id = a.employee_id " +
                         "LEFT JOIN Role r ON a.role_id = r.role_id;";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          EmployeeInfo e = new EmployeeInfo();
          e.setFromRS(rs);
          list.add(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  
  public Employee getEmployeeByID(int id) {
    String sql = "SELECT * " +
                         "FROM Employee " +
                         "WHERE employee_id = ?";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Employee e = new Employee();
          e.setFromRS(rs);
          return e;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    return null;
  }
  
  public void insertEmployee(Employee e) {
    if (e == null) return;
    String sql = "INSERT INTO Employee (employee_name, gender, birthday, address, phone_number, salary, status) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
    DBHelper.Instance().executeUpd(sql,
            e.getFullName(),
            e.getGender(),
            e.getBirthday(),
            e.getAddress(),
            e.getPhoneNumber(),
            e.getSalary(),
            e.getStatus());
  }
  
  public void updateEmployee(Employee e) {
    if (e == null) return;
    String sql = "UPDATE Employee " +
                         "SET employee_name = ?, gender = ?, birthday = ?, address = ?, phone_number = ?, salary = ?, status = ? " +
                         "WHERE employee_id = ?";
    DBHelper.Instance().executeUpd(sql,
            e.getFullName(),
            e.getGender(),
            e.getBirthday(),
            e.getAddress(),
            e.getPhoneNumber(),
            e.getSalary(),
            e.getStatus(),
            e.getEmployeeId());
  }
  
  public void deleteEmployee(int id) {
    String sql = "DELETE FROM Employee WHERE employee_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
}
