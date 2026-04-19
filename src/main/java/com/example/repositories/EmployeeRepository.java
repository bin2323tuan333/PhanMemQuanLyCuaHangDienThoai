package com.example.repositories;

import com.example.models.Employee;
import com.example.utils.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRepository {
  public Employee getEmployeeByID(int id) {
    String sql = "SELECT *, CASE WHEN gender = 'Nam' THEN 1 ELSE 0 END as gender_bool FROM Employee WHERE employee_id = ?";
    ResultSet rs = null;
    try {
      rs = DBHelper.Instance().executeQuery(sql, id);
      if (rs.next()) {
        Employee e = new Employee();
        e.setEmployeeId(rs.getInt("employee_id"));
        e.setFullName(rs.getString("employee_name"));
        e.setAddress(rs.getString("address"));
        e.setBirthday(rs.getDate("birthday"));
        e.setGender(rs.getBoolean("gender_bool"));
        e.setSalary(rs.getDouble("salary"));
        e.setStatus(rs.getString("status"));
        e.setPhoneNumber(rs.getString("phone_number"));
        return e;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (rs != null) {
          Connection conn = rs.getStatement().getConnection();
          rs.close();
          if (conn != null && !conn.isClosed()) {
            conn.close();
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return null;
  }
  
}
