package com.example.models;

import java.util.Date;

public class Bill {
    private int billId;
    private Date invoiceDate;
    private double totalAmount;
    private int employeeId;
    private int customerId;

    public Bill() {}

    public Bill(int billId, Date invoiceDate, double totalAmount, int employeeId, int customerId) {
        this.billId = billId;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.employeeId = employeeId;
        this.customerId = customerId;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    @Override
    public String toString() {
        return "Bill(id = " + this.billId + ", date = " + invoiceDate + ", total = " + totalAmount + ")";
    }
}