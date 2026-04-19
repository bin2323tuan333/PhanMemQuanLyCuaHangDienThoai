package com.example.services;

import com.example.DTO.RecentBill;
import com.example.models.Bill;
import com.example.models.Customer;
import com.example.models.Employee;
import com.example.repositories.BillRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.EmployeeRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillService {
    private BillRepository billRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public BillService() {
        billRepository = new BillRepository();
        customerRepository = new CustomerRepository();
        employeeRepository = new EmployeeRepository();
    }

    public List<RecentBill> getAllBills() throws SQLException {
        List<Bill> bills = billRepository.getAllBills();
        List<RecentBill> recentBills = new ArrayList<>();

        for (Bill bill : bills) {
            Customer customer = null;
            Employee employee = null;

            if (bill.getCustomerId() > 0) {
                customer = customerRepository.getCustomerById(bill.getCustomerId());
            }

            RecentBill recentBill = new RecentBill(
                    bill.getBillId(),
                    customer != null ? customer.getFullName() : "Khách lẻ",
                    bill.getInvoiceDate(),
                    bill.getTotalAmount(),
                    "COMPLETED" // Hoặc lấy từ database nếu có cột status
            );

            recentBills.add(recentBill);
        }

        return recentBills;
    }
}