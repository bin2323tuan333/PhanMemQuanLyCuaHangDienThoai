package com.example.services;

import com.example.DTO.BillDetailInfo;
import com.example.DTO.BillInfo;
import com.example.DTO.ImportBillInfo;
import com.example.DTO.RecentBill;
import com.example.models.Bill;
import com.example.models.Customer;
import com.example.models.Employee;
import com.example.repositories.*;

import java.sql.SQLException;
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
  
  public void deleteBill(int id) throws SQLException {
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
  
  public List<RecentBill> getAllBills() {
    List<Bill> list = billRepository.getAllBills();
    List<RecentBill> recentBills = new ArrayList<>();
    
    for (Bill item : list) {
      Customer customer = null;
      Employee employee = null;
      
      if (item.getCustomerId() > 0) {
        customer = customerRepository.getCustomerById(item.getCustomerId());
      }
      if (item.getEmployeeId() > 0) {
        employee = employeeRepository.getEmployeeByID(item.getEmployeeId());
      }
      
      String customerName = (customer != null) ? customer.getFullName() : "Không có";
      String employeeName = (employee != null) ? employee.getFullName() : "Không có";
      
      RecentBill recentBill = new RecentBill(
              item.getBillId(),
              customerName,
              item.getInvoiceDate(),
              item.getTotalAmount(),
              employeeName,
              "COMPLETED"
      
      );
      
      recentBills.add(recentBill);
    }
    
    return recentBills;
  }
  
  public List<BillDetailInfo> getBillDetailInfoByBillId(int id) {
    BillDetailRepository billDetailRepository = new BillDetailRepository();
    return billDetailRepository.getBillDetailInfosByBillId(id);
  }
  
  public void updateBill(Bill billInfo) throws SQLException {
    billRepository.updateBill(billInfo);
  }
  public  void addBill(Bill billInfo) throws SQLException {
    billRepository.insertBill(billInfo);
  }
  public double getTotalRevenue() {
    return billRepository.getTotalRevenue();
  }
  public double getTotalOrders() {
    return billRepository.getTotalOrders();
  }
  
  
}