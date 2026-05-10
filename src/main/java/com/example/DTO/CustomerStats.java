package com.example.DTO;

public class CustomerStats {
  private String name, phone;
  private int orderCount;
  private double totalSpent;
  
  public CustomerStats(String name, String phone, int orderCount, double totalSpent) {
    this.name = name;
    this.phone = phone;
    this.orderCount = orderCount;
    this.totalSpent = totalSpent;
  }
  
  public String getName() {
    return name;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public int getOrderCount() {
    return orderCount;
  }
  
  public double getTotalSpent() {
    return totalSpent;
  }
  
  public String getTotalSpentDisplay() {
    return String.format("%,.0f đ", totalSpent);
  }
}
