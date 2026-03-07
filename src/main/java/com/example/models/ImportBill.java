package com.example.models;

import java.util.Date;

public class ImportBill {
    private int importId;
    private Date importDate;
    private double totalAmount;

    private int employeeId; // FK -> Employee

    public ImportBill() {}

    public ImportBill(int importId, Date importDate, double totalAmount, int employeeId) {
        this.importId = importId;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
        this.employeeId = employeeId;
    }

    public int getImportId() { return importId; }
    public void setImportId(int importId) { this.importId = importId; }

    public Date getImportDate() { return importDate; }
    public void setImportDate(Date importDate) { this.importDate = importDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    @Override
    public String toString() {
        return "ImportBill#" + importId;
    }
}