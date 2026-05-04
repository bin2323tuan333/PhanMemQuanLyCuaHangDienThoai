package com.example.DTO;

import com.example.models.ImportBill;
import com.example.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportBillDetailInfo {
  private int importBillDetailId;
  private ImportBill bill;
  private Product product;
  private int quantity;
  private double unitPrice;
  
  public ImportBillDetailInfo() {
  }
  
  public ImportBillDetailInfo(int importBillDetailId, ImportBill bill, Product product, int quantity, double unitPrice) {
    this.importBillDetailId = importBillDetailId;
    this.bill = bill;
    this.product = product;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }
  
  public void setFromRS(ResultSet rs) throws SQLException {
    this.importBillDetailId = rs.getInt("import_detail_id");
    this.quantity = rs.getInt("quantity");
    this.unitPrice = rs.getDouble("unit_price");
    
    this.bill = new ImportBill();
    this.bill.setImportId(rs.getInt("import_id"));
    
    this.product = new Product();
    this.product.setFromRS(rs);
  }
  
  public int getImportBillDetailId() {
    return importBillDetailId;
  }
  
  public void setImportBillDetailId(int importBillDetailId) {
    this.importBillDetailId = importBillDetailId;
  }
  
  public ImportBill getBill() {
    return bill;
  }
  
  public void setBill(ImportBill bill) {
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
