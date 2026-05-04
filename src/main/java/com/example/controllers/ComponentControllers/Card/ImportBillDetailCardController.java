package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.ImportBillDetailInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ImportBillDetailCardController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_quantity;
  @FXML
  private Label lb_price;
  
  private ImportBillDetailInfo importBillDetailInfo;
  
  public void setImportBillDetailInfo(ImportBillDetailInfo importBillDetailInfo) {
    this.importBillDetailInfo = importBillDetailInfo;
    setup();
  }
  
  public void setup() {
    this.lb_name.setText(importBillDetailInfo.getProduct().getProductName());
    this.lb_quantity.setText(importBillDetailInfo.getQuantity() + "");
    this.lb_price.setText(String.format("%,.0f", importBillDetailInfo.getQuantity() * importBillDetailInfo.getProduct().getPrice()) + " đ");
  }
}
