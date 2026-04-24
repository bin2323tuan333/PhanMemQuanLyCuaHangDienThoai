package com.example.controllers.AdminControllers;


import com.example.DTO.ProductSale;
import com.example.DTO.RecentBill;
import com.example.services.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

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
