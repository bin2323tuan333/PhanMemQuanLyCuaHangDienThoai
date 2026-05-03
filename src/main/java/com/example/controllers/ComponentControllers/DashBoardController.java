package com.example.controllers.ComponentControllers;


import com.example.DTO.BillInfo;
import com.example.services.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
  
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    RevenueService revenueService = new RevenueService();
    lb_revenue.setText(String.format("%,.0f VNĐ", revenueService.getMonthRevenue()));
    lb_product.setText(revenueService.getRemainingProduct() + "");
    lb_customer.setText(revenueService.getTotalCustomers() + "");
    lb_order.setText(revenueService.getThisMonthOrders() + "");
  }
  
  public void handleBtnAddBill() {
    BillService billService = new BillService();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      List<BillInfo> list = billService.getAllBillInfos();
    } catch (Exception e) {
    }
  }
  
  public void handleBtnAddCustomer() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CustomerForm.fxml"));
      Parent root = loader.load();
      CustomerFormController customerFormController = loader.getController();
      customerFormController.setCustomerInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAddImportBill() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateImportBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
    } catch (Exception e) {
    }
  }
  
  public void handleBtnAddProduct() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductForm.fxml"));
      Parent root = loader.load();
      ProductFormController productFormController = loader.getController();
      productFormController.setProductInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void renderTopBrand() {
  
  }
  
  public void renderTopProduct() {
  
  }
  
  public void renderBill() {
  
  }
}
