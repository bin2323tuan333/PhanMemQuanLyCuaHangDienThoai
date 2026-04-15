package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
  private String roleName;
  private int roleId;
  
  public Role() {
    
  }
  
  public Role(int id, String name) {
    this.roleId = id;
    this.roleName = name;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.roleId = rs.getInt("role_id");
    this.roleName = rs.getString("role_name");
  }
  
  public void setRoleId(int id) {
    this.roleId = id;
  }
  
  public int getRoleId() {
    return this.roleId;
  }
  
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  
  public String getRoleName() {
    return this.roleName;
  }
  
  
}
