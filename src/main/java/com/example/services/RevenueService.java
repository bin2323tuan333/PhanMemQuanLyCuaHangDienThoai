package com.example.services;

import com.example.DTO.BillInfo;
import com.example.DTO.BrandReport;
import com.example.repositories.BillRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RevenueService {
  public List<BrandReport> getBrandReport() {
    ProductRepository productRepository = new ProductRepository();
    List<BrandReport> allReports = productRepository.getProductCountByBrand();
    if (allReports.size() <= 4) {
      return allReports;
    }
    
    List<BrandReport> dashboardReports = new ArrayList<>(allReports.subList(0, 3));
    int otherTotal = 0;
    for (int i = 3; i < allReports.size(); i++) {
      otherTotal += allReports.get(i).getTotal();
    }
    
    dashboardReports.add(new BrandReport("Còn lại", otherTotal));
    
    return dashboardReports;
  }
  
  public List<BillInfo> getTenBillInfoRecent() {
    BillRepository billRepository = new BillRepository();
    return billRepository.getTenBillInfosRecent();
  }
  
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
