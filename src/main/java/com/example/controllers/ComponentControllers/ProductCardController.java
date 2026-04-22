package com.example.controllers.ComponentControllers;

import com.example.models.Brand;
import com.example.models.Product;
import com.example.services.BrandService;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
  
  
  @FXML
  public void initialize() {
//    try {
//      setup();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
  
  private void setup(int id) {
    ProductService productService = new ProductService();
    BrandService brandService = new BrandService();
    Product product = productService.getProductById(id);
    Brand brand = brandService.getBrandById(product.getBrandId());
    
    this.lb_name.setText(product.getProductName());
    
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_price.setText(df.format(product.getPrice()) + " VNĐ");
    this.lb_stock.setText("" + product.getStock());
    
    if (brand != null)
      this.lb_brand.setText(brand.getBrandName());
  }
  
  
  public int getProductId() {
    return productId;
  }
  
  public void setProductId(int productId) {
//    this.productId = productId;
    setup(productId);
  }
}
