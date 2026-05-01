package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.CreateBillController;
import com.example.controllers.ComponentControllers.CustomerFormController;
import com.example.controllers.ComponentControllers.ProductFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
  private ProductInfo productInfo;
  
  public void setCreateBillController(CreateBillController createBillController) {
    this.createBillController = createBillController;
  }
  
  public void setSale(boolean sale) {
    this.isSale = sale;
  }
  
  @FXML
  public void initialize() {
    this.isSale = false;
  }
  
  private void setup(ProductInfo p) {
    this.productId = p.getProductId();
    this.lb_name.setText(p.getProductName());
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_price.setText(df.format(p.getPrice()) + " VNĐ");
    this.lb_stock.setText("" + p.getStock());
    this.lb_brand.setText(p.getBrand().getBrandName());
  }
  
  public void handleClick() {
    if (this.isSale) {
      if (this.createBillController != null) {
        this.createBillController.addProductEngine(this.productId);
      }
      this.createBillController.caculate();
    } else {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductForm.fxml"));
        Parent root = loader.load();
        ProductFormController productFormController = loader.getController();
        productFormController.setProductInfo(this.productInfo);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Thêm mới");
        stage.showAndWait();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public int getProductId() {
    return productId;
  }
  
  public void setProduct(ProductInfo p) {
    this.productInfo = p;
    setup(p);
  }
  
  
}
