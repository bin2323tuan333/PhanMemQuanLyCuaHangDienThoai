package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.ProductReport;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProductReportController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_quantity;
  
  private ProductReport productReport;
  
  public void setProductReport(ProductReport productReport) {
    this.productReport = productReport;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.lb_name.setText(productReport.getName());
    this.lb_quantity.setText(productReport.getQuantity() + "");
  }
}
