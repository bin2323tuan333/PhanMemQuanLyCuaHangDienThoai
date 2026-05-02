package com.example.services;

import com.example.DTO.EmployeeInfo;
import com.example.models.Employee;
import com.example.repositories.EmployeeRepository;

import java.util.List;

public class EmployeeService {
  public Employee getEmployeeByID(int id) {
    EmployeeRepository eRepo = new EmployeeRepository();
    return eRepo.getEmployeeByID(id);
  }
  
  public List<EmployeeInfo> getAllEmployeeInfos() {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    return employeeRepository.getAllEmployeeInfo();
  }
  
  public EmployeeInfo getEmployeeInfoByID(int id) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    return employeeRepository.getEmployeeInfoByID(id);
  }
  public void updateEmployee(EmployeeInfo emp) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateEmployee(emp);
  }
  public void addEmployee(EmployeeInfo emp) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.insertEmployee(emp);
  }
  public void deleteEmployee(int employeeId) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.deleteEmployee(employeeId);
  }
}
