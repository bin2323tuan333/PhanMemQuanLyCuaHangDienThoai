package com.example.repositories;

import com.example.models.ImportBill;
import com.example.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
  public List<Role> getAllRoles() {
    List<Role> list = new ArrayList<>();
    String sql = "SELECT * FROM role";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Role r = new Role();
          r.setFromRS(rs);
          list.add(r);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  
  public Role getRoleByID(int id) {
    String sql = "SELECT * FROM role WHERE role_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Role r = new Role();
          r.setFromRS(rs);
          return r;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
  
  public void insertRole(Role role) {
    String sql = "INSERT INTO role (role_name) VALUES (?)";
    DBHelper.Instance().executeUpd(sql, role.getRoleName());
  }
  
  public void updateRole(Role role) {
    String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
    DBHelper.Instance().executeUpd(sql, role.getRoleName(), role.getRoleId());
  }
  
  public void deleteRole(int id) {
    String sql = "DELETE FROM role WHERE role_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
}
