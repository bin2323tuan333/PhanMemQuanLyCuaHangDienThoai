package com.example.models;

public class BillDetail {
    private int billId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public BillDetail() {};
    public BillDetail(int billId, int productId, int quantity, double unitPrice) {
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        return "BillDetail(billId = " + this.billId + "productId = " + this.productId + ", quantity = " + this.quantity + ", unitPrice = " + this.unitPrice + ")";
    }
}
