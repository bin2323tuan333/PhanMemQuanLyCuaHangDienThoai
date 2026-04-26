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
}
