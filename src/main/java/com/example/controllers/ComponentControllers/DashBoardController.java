package com.example.controllers.ComponentControllers;


import com.example.services.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashBoardController {
  @FXML
  private Label lb_revenue;
  @FXML
  private Label lb_product;
  @FXML
  private Label lb_customer;
  @FXML
  private Label lb_order;
  
  
  private IRenevueService revenueService;
  private IStatisticsService statisticsService;
  private IBillService billService;
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    revenueService = new TempRenevueService();
    statisticsService = new TempStatisticsService();
    billService = new TempBillService();
    
    
    lb_revenue.setText(String.format("%,.0f VNĐ", statisticsService.getTotalRevenueToday()));
    lb_product.setText(statisticsService.getRemainingProducts() + "");
    lb_customer.setText(statisticsService.getTotalCustomers() + "");
    lb_order.setText(statisticsService.getNewOrdersCount() + "");
  }
  
  public void handleBtnAddBill() {
  
  }
  
  public void handleBtnAddCustomer() {
  
  }
  
  public void handleBtnAddImportBill() {
  
  }
  
  public void handleBtnAddProduct() {
  
  }
  
}
