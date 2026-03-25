package com.example.DTO;

import java.util.Date;

public class RecentBill {
    private int billId;
    private String customerName;
    private Date date;
    private double total;
    private String status;

    public RecentBill(int billId, String customerName, Date date, double total, String status) {
        this.billId = billId;
        this.customerName = customerName;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public int getBillId() { return billId; }
    public String getCustomerName() { return customerName; }
    public Date getDate() { return date; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}
