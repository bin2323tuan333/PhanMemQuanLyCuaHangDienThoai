package com.example.DTO;

import java.sql.ResultSet;
import java.util.Date;

public class RecentBill {
  private int billId;
  private String customerName;
  private Date date;
  private double total;
  private String status;
  private String employeeName;

  public  RecentBill() {
  }
  public RecentBill(int billId, String customerName, Date date, double total, String employeeName) {
    this.billId = billId;
    this.customerName = customerName;
    this.date = date;
    this.total = total;
    this.employeeName = employeeName;
    this.status = "COMPLETED";
  }
  public void setFromRS(ResultSet rs) throws Exception {
    this.billId = rs.getInt("bill_id");
    this.customerName = rs.getString("customer_name");
    this.date = rs.getDate("invoice_date");
    this.total = rs.getDouble("total_amount");
    this.employeeName = rs.getString("employee_name");
        this.status = rs.getString("status");
  }
  
  public RecentBill(int billId, String customerName, Date date, double total, String employeeName, String status) {
    this.billId = billId;
    this.customerName = customerName;
    this.date = date;
    this.total = total;
    this.employeeName = employeeName;
    this.status = status;
  }
  
  public int getBillId() {
    return billId;
  }
  
  public String getCustomerName() {
    return customerName;
  }
  
  public Date getDate() {
    return date;
  }
  
  public double getTotal() {
    return total;
  }
  
  public String getStatus() {
    return status;
  }
  
  public String getEmployeeName() {
    return employeeName;
  }
  
  // Setters
  public void setBillId(int billId) {
    this.billId = billId;
  }
  
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public void setTotal(double total) {
    this.total = total;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }


}