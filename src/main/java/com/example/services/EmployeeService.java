package com.example.services;

import com.example.models.Employee;
import com.example.repositories.EmployeeRepository;

public class EmployeeService {
  public Employee getEmployeeByID(int id) {
    EmployeeRepository eRepo = new EmployeeRepository();
    return eRepo.getEmployeeByID(id);
  }
}
