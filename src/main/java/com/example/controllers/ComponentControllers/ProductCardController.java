package com.example.controllers.ComponentControllers;

import com.example.DTO.ProductInfo;
import com.example.models.Brand;
import com.example.models.Product;
import com.example.services.BrandService;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProductCardController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_price;
  @FXML
  private Label lb_stock;
  @FXML
  private Label lb_brand;
  
  private int productId;
  private boolean isSale;
  private CreateBillController createBillController;
  
  public void setCreateBillController(CreateBillController createBillController) {
    this.createBillController = createBillController;
  }
  
  
  @FXML
  public void initialize() {
//    try {
//      setup();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
  
  private void setup(ProductInfo p) {
    this.lb_name.setText(p.getProductName());
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_price.setText(df.format(p.getPrice()) + " VNĐ");
    this.lb_stock.setText("" + p.getStock());
    this.lb_brand.setText(p.getBrandName());
  }
  
  public void handleClick() {
    this.isSale = true;
    if (this.isSale) {
      if (this.createBillController != null) {
        this.createBillController.addProductEngine(this.productId);
      }
    }
  }
  
  public int getProductId() {
    return productId;
  }
  
  public void setProduct(ProductInfo p) {
    this.productId = p.getProductId();
    setup(p);
  }
}
