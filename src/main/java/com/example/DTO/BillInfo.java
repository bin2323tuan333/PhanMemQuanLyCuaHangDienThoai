package com.example.DTO;

import com.example.models.Customer;
import com.example.models.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillInfo {
  private int billId;
  private Date invoiceDate;
  private double totalAmount;
  private Employee employee;
  private Customer customer;
  private boolean status;
  
  public BillInfo() {
  }
  
  public BillInfo(Date invoiceDate, int billId, double totalAmount, Employee employee, Customer customer, boolean status) {
    this.invoiceDate = invoiceDate;
    this.billId = billId;
    this.totalAmount = totalAmount;
    this.employee = employee;
    this.customer = customer;
    this.status = status;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.billId = rs.getInt("bill_id");
    this.invoiceDate = rs.getDate("invoice_date");
    this.totalAmount = rs.getDouble("total_amount");
    this.status = rs.getBoolean("status");
    Employee emp = new Employee();
    emp.setFromRS(rs);
    this.employee = emp;
    Customer cust = new Customer();
    cust.setFromRS(rs);
    this.customer = cust;
  }
  
  public boolean getStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public int getBillId() {
    return billId;
  }
  
  public void setBillId(int billId) {
    this.billId = billId;
  }
  
  public Date getInvoiceDate() {
    return invoiceDate;
  }
  
  public void setInvoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
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
  
  public Customer getCustomer() {
    return customer;
  }
  
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
