package com.example.services;

import com.example.DTO.EmployeeInfo;
import com.example.models.Account;
import com.example.models.Employee;
import com.example.repositories.AccountRepository;
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
  
  public List<EmployeeInfo> searchEmployees(String keyword) {
    EmployeeRepository employeeRepo = new EmployeeRepository();
    if (keyword == null || keyword.trim().isEmpty()) {
      return employeeRepo.getAllEmployeeInfo();
    }
    return employeeRepo.searchEmployeeInfo(keyword);
  }
  
  public int getEmployeeIdByPhone(String phone) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    return employeeRepository.getEmployeeIdByPhone(phone);
  }
  
  
  public void updateEmployee(Employee emp) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateEmployee(emp);
  }
  
  public void addEmployee(Employee emp) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.insertEmployee(emp);
  }
  
  public void deleteEmployee(int employeeId) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    AccountService accountService = new AccountService();
    Account acc = accountService.getAccountByEmployeeId(employeeId);
    AccountRepository accountRepository = new AccountRepository();
    accountRepository.deleteAccount(acc.getAccountId());
    employeeRepository.deleteEmployee(employeeId);
  }
}
