package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportBillDetail {
    private int importId;  // FK -> ImportBill
    private int productId; // FK -> Product
    private int quantity;
    private double unitPrice;

    public void setFromRS(ResultSet rs) throws SQLException {
        this.importId = rs.getInt("import_id");
        this.productId = rs.getInt("product_id");
        this.quantity = rs.getInt("quantity");
        this.unitPrice = rs.getDouble("unit_price");
    }

    public ImportBillDetail() {}

    public ImportBillDetail(int importId, int productId, int quantity, double unitPrice) {
        this.importId = importId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getImportId() { return importId; }
    public void setImportId(int importId) { this.importId = importId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getSubTotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "ImportDetail(importId=" + importId + ", productId=" + productId + ", qty=" + quantity + ")";
    }
}
