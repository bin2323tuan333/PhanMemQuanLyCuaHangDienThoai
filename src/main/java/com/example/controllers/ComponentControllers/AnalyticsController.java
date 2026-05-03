package com.example.controllers.ComponentControllers;

import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnalyticsController {
  
  @FXML
  private Label totalRevenue;
  @FXML
  private Label totalOrders;
  @FXML
  private Label avgOrderValue;
  @FXML
  private Label customerGrowth;
  
  
  @FXML
  public void initialize() {
    BillService bill = new BillService();

    double revenue = bill.getTotalRevenue();
    double orders = bill.getTotalOrders();
    double avgValue = orders > 0 ? revenue / orders : 0;

    totalRevenue.setText(String.format("%,.0f", revenue) + " đ");
    totalOrders.setText(String.valueOf((int) orders));
    avgOrderValue.setText(String.format("%,.0f", avgValue) + " đ");
    customerGrowth.setText("N/A");
    

  }
}