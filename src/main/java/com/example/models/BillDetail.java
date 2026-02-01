package com.example.models;

public class BillDetail {
    public int billDetailId;
    public int quantity;
    public double unitPrice;

    public BillDetail() {};
    public BillDetail(int billDetailId, int quantity, double unitPrice) {
        this.billDetailId = billDetailId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "BillDetail(id = " + this.billDetailId + ", quantity = " + this.quantity + ", unitPrice = " + this.unitPrice + ")";
    }
}
