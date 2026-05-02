package com.example.services;

import com.example.repositories.BillRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.ProductRepository;

public class RevenueService {
  public double getMonthRevenue() {
    BillRepository billRepository = new BillRepository();
    return billRepository.getRevenueMonth();
  }
  
  public int getRemainingProduct() {
    ProductRepository productRepository = new ProductRepository();
    return productRepository.getTotalRemainingStock();
  }
  
  public int getTotalCustomers() {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getTotalCustomers();
  }
  
  public int getThisMonthOrders() {
    BillRepository billRepository = new BillRepository();
    return billRepository.getThisMonthOrders();
  }
}
