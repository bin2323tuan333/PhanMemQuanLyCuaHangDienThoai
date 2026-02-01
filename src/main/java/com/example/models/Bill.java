package com.example.models;

import java.util.Date;

public class Bill {
    public int billId;
    public Date invoiceDate;
    public double totalAmount;

    public Bill() {}
    public Bill(int billId, Date invoiceDate, double totalAmount) {
        this.billId = billId;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    @Override
    public String toString() {
        return "Bill(id = " + this.billId + ", date = " + (this.invoiceDate).toString() + ", totalAmmount = " + this.totalAmount +")";
    }
}
