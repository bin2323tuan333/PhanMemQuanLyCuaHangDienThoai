package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetail {
  private int billDetailId;
  private int billId;
  private int productId;
  private int quantity;
  private double unitPrice;
  
  public BillDetail() {
  }
  
  ;
  
  public BillDetail(int billDetailId, int billId, int productId, int quantity, double unitPrice) {
    this.billDetailId = billDetailId;
    this.billId = billId;
    this.productId = productId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.billDetailId = rs.getInt("bill_detail_id");
    this.billId = rs.getInt("bill_id");
    this.productId = rs.getInt("product_id");
    this.quantity = rs.getInt("quantity");
    this.unitPrice = rs.getDouble("unit_price");
  }
  
  public int getbillDetailId() {
    return billDetailId;
  }
  
  public void setbillDetailId(int billDetailId) {
    this.billDetailId = billDetailId;
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
