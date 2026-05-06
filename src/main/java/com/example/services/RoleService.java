package com.example.services;

import com.example.models.Role;
import com.example.repositories.RoleRepository;

import java.util.List;

public class RoleService {
  private final RoleRepository roleRepository;
  
  public RoleService() {
    this.roleRepository = new RoleRepository();
  }
  
  public List<Role> getAllRoles() {
    return roleRepository.getAllRoles();
  }
  
  public Role getRoleByID(int id) {
    return roleRepository.getRoleByID(id);
  }
  
  public void insertRole(Role role) {
    if (role.getRoleName() == null || role.getRoleName().trim().isEmpty()) {
      throw new IllegalArgumentException("Role name cannot be empty");
    }
    roleRepository.insertRole(role);
  }
  
  public void updateRole(Role role) {
    if (role.getRoleId() <= 0 || role.getRoleName() == null || role.getRoleName().trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid role data");
    }
    roleRepository.updateRole(role);
  }
  
  public void deleteRole(int id) {
    
    roleRepository.deleteRole(id);
  }
}
