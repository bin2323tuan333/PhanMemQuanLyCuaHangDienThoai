package com.example.DTO;

import com.example.models.Brand;
import com.example.models.Employee;
import com.example.models.Supplier;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportBillInfo {
  private int importId;
  private Date importDate;
  private double totalAmount;
  private Employee employee;
  private Supplier supplier;
  
  public ImportBillInfo() {
  }
  
  public ImportBillInfo(int importId, Date importDate, double totalAmount, Employee employee, Supplier supplier) {
    this.importId = importId;
    this.importDate = importDate;
    this.totalAmount = totalAmount;
    this.employee = employee;
    this.supplier = supplier;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.importId = rs.getInt("import_id");
    this.importDate = rs.getDate("import_date");
    this.totalAmount = rs.getDouble("total_amount");
    
    Employee emp = new Employee();
    emp.setEmployeeId(rs.getInt("employee_id"));
    emp.setFullName(rs.getString("employee_name"));
    this.employee = emp;
    
    Supplier sup = new Supplier();
    sup.setSupplierId(rs.getInt("supplier_id"));
    sup.setName(rs.getString("name"));
    this.supplier = sup;
  }
  
  public int getImportId() {
    return importId;
  }
  
  public void setImportId(int importId) {
    this.importId = importId;
  }
  
  public Date getImportDate() {
    return importDate;
  }
  
  public void setImportDate(Date importDate) {
    this.importDate = importDate;
  }
  
  public double getTotalAmount() {
    return totalAmount;
  }
  
  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }
  
  public Employee getEmployee() {
    return employee;
  }
  
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  
  public Supplier getSupplier() {
    return supplier;
  }
  
  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
  }
}
