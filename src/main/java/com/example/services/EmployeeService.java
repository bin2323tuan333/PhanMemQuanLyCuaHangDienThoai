package com.example.services;

import com.example.DTO.EmployeeInfo;
import com.example.DTO.EmployeeStats;
import com.example.models.Account;
import com.example.models.Employee;
import com.example.repositories.AccountRepository;
import com.example.repositories.EmployeeRepository;

import java.util.List;

public class EmployeeService {
  public boolean isValidData(Employee employee) {
    if (employee == null) {
      throw new IllegalArgumentException("Dữ liệu nhân viên không được để trống.");
    }
    if (employee.getFullName() == null || employee.getFullName().trim().isEmpty()) {
      throw new IllegalArgumentException("Họ và tên không được để trống.");
    }
    if (employee.getPhoneNumber() == null || employee.getPhoneNumber().trim().isEmpty()) {
      throw new IllegalArgumentException("Số điện thoại không được để trống.");
    }
    if (!employee.getPhoneNumber().matches("\\d+")) {
      throw new IllegalArgumentException("Số điện thoại chỉ được chứa ký tự số.");
    }
    if (employee.getSalary() < 0) {
      throw new IllegalArgumentException("Mức lương không thể là số âm.");
    }
    if (employee.getBirthday() == null) {
      throw new IllegalArgumentException("Vui lòng chọn ngày sinh.");
    }
    return true;
  }
  
  public List<EmployeeStats> getTopEmployees() {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    return employeeRepository.getTopEmployees();
  }
  
  public boolean isDuplicatePhone(Employee employee) {
    return false;
  }
  
  public boolean canDelete(Employee employee) {
//    - rang buoc hoa don
    return false;
  }
  
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
  public boolean hasBill  (int employeeId) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    return employeeRepository.hasBill(employeeId);
  }
  public boolean isCurrentLoggedInEmployee(int employeeId) {
    AccountService accountService = new AccountService();
    int currentAccount = accountService.getCurrentAccountId();
    return accountService.getAccountByID(currentAccount).getEmployeeId() == employeeId;
  }
}
