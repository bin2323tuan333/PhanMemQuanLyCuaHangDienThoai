package com.example.services;

import com.example.DTO.*;
import com.example.models.*;
import com.example.repositories.*;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class BillService {
  private BillRepository billRepository;
  private ImportBillRepository importBillRepository;
  private CustomerRepository customerRepository;
  private EmployeeRepository employeeRepository;
  
  
  public BillService() {
    billRepository = new BillRepository();
    customerRepository = new CustomerRepository();
    employeeRepository = new EmployeeRepository();
    importBillRepository = new ImportBillRepository();
  }
  
  
  public List<XYChart.Data<String, Number>> getMonthlyRevenue() {
    return billRepository.getMonthlyRevenue();
  }
  
  
  public void deleteBill(int id) {
    billRepository.deleteBill(id);
  }
  
  public List<ImportBillInfo> getAllImportBillInfo() {
    return importBillRepository.getAllImportBillInfos();
  }
  
  public List<BillInfo> getAllBillInfos() {
    return billRepository.getAllBillInfos();
  }
  
  public List<BillDetailInfo> getAllBillDetailInfos() {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    return billDetailRepository.getAllBillDetailInfos();
  }
  
  public BillInfo getBillInfoByID(int id) {
    return billRepository.getBillInfoByID(id);
  }
  
  public List<BillInfo> filterBills(String keyword, LocalDate fromDate, LocalDate toDate) {
    Date from = (fromDate != null) ? Date.valueOf(fromDate) : null;
    Date to = (toDate != null) ? Date.valueOf(toDate) : null;
    return billRepository.filterBills(keyword, from, to);
  }
  
  public List<BillDetailInfo> getBillDetailInfoByBillId(int id) {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    return billDetailRepository.getBillDetailInfosByBillId(id);
  }
  
  public List<ImportBillDetailInfo> getImportBillDetailInfoByBillId(int id) {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    return billDetailRepository.getImportBillDetailInfosByBillId(id);
  }
  
  public void updateBill(Bill billInfo) {
    billRepository.updateBill(billInfo);
  }
  
  public int addBill(Bill billInfo) {
    return billRepository.addBill(billInfo);
  }
  
  public int addImportBill(ImportBill billInfo) {
    return importBillRepository.addImportBill(billInfo);
  }
  
  public double getTotalRevenue() {
    return billRepository.getTotalRevenue();
  }
  
  public double getTotalOrders() {
    return billRepository.getTotalOrders();
  }
  
  
  public void updateImportBill(ImportBillInfo importBillInfo) {
    importBillRepository.updateImportBill(importBillInfo);
  }
  
  public void deleteImportBill(int id) {
    importBillRepository.deleteImportBill(id);
  }
  
  public void deleteBillDetail(int id) {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    billDetailRepository.deleteBillDetail(id);
  }
  
  public void addBillDetail(BillDetail billDetailInfo) throws SQLException {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    billDetailRepository.insertBillDetail(billDetailInfo);
  }
  
  public void updateBillDetail(BillDetail billDetailInfo) throws SQLException {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    billDetailRepository.updateBillDetail(billDetailInfo);
  }
  
  public List<ImportBillInfo> searchImportBills(String key) {
    return importBillRepository.searchImportBills(key);
  }
  
}