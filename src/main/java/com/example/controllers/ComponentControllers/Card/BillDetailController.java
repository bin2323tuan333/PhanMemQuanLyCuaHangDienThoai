package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.BillDetailInfo;
import com.example.DTO.RecentBill;
import com.example.models.BillDetail;
import com.example.models.Category;
import com.example.services.BillService;
import com.example.services.CategoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class BillDetailController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_quantity;
  @FXML
  private Label lb_price;
  
  private BillDetailInfo billDetailInfo;
  
  public void setBillDetailInfo(BillDetailInfo billDetailInfo) {
    this.billDetailInfo = billDetailInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    if (billDetailInfo != null) {
      lb_name.setText(billDetailInfo.getProduct().getProductName());
      lb_quantity.setText(String.valueOf(billDetailInfo.getQuantity()));
      lb_price.setText(String.format("%,.0f", billDetailInfo.getQuantity() * billDetailInfo.getProduct().getPrice()) + " đ");
    }
  }
}
