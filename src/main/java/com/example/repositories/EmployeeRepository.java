package com.example.repositories;

import com.example.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
  public Employee getEmployeeByID(int id) {
    String sql = "SELECT *, CASE WHEN gender = 'Nam' THEN 1 ELSE 0 END as gender_bool FROM Employee WHERE employee_id = ?";
    
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
  
}
