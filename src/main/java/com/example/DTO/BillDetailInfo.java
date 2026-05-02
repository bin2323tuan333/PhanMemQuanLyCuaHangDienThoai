package com.example.DTO;

import com.example.models.Bill;
import com.example.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailInfo {
  private int billDetailId;
  private Bill bill;
  private Product product;
  private int quantity;
  private double unitPrice;
  
  public BillDetailInfo() {
  }
  
  public BillDetailInfo(int billDetailId, Bill bill, Product product, int quantity, double unitPrice) {
    this.billDetailId = billDetailId;
    this.bill = bill;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.billDetailId = rs.getInt("bill_detail_id");
    this.quantity = rs.getInt("quantity");
    this.unitPrice = rs.getDouble("unit_price");
    
    this.bill = new Bill();
    this.bill.setBillId(rs.getInt("bill_id"));
    
    this.product = new Product();
    this.product.setFromRS(rs);
  }
  
  public int getBillDetailId() {
    return billDetailId;
  }
  
  public void setBillDetailId(int billDetailId) {
    this.billDetailId = billDetailId;
  }
  
  public Bill getBill() {
    return bill;
  }
  
  public void setBill(Bill bill) {
    this.bill = bill;
  }
  
  public Product getProduct() {
    return product;
  }
  
  public void setProduct(Product product) {
    this.product = product;
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
}
