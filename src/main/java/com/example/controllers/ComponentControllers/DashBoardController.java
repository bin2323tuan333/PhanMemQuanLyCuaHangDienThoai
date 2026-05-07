package com.example.controllers.ComponentControllers;


import com.example.DTO.BillInfo;
import com.example.DTO.BrandReport;
import com.example.controllers.ComponentControllers.Form.CustomerFormController;
import com.example.controllers.ComponentControllers.Form.ProductFormController;
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
  private Label lb_top1_name;
  @FXML
  private Label lb_top1_value;
  @FXML
  private Label lb_top2_name;
  @FXML
  private Label lb_top2_value;
  @FXML
  private Label lb_top3_name;
  @FXML
  private Label lb_top3_value;
  @FXML
  private Label lb_top4_name;
  @FXML
  private Label lb_top4_value;
  
  
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
    renderTopBrand();
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/CustomerForm.fxml"));
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/ProductForm.fxml"));
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
    RevenueService revenueService = new RevenueService();
    List<BrandReport> reports = revenueService.getBrandReport();
    
    if (reports.isEmpty()) return;
    
    if (reports.size() >= 1) {
      lb_top1_name.setText(reports.get(0).getBrandName());
      lb_top1_value.setText(reports.get(0).getTotal() + " sản phẩm");
    }
    if (reports.size() >= 2) {
      lb_top2_name.setText(reports.get(1).getBrandName());
      lb_top2_value.setText(reports.get(1).getTotal() + " sản phẩm");
    }
    if (reports.size() >= 3) {
      lb_top3_name.setText(reports.get(2).getBrandName());
      lb_top3_value.setText(reports.get(2).getTotal() + " sản phẩm");
    }
    if (reports.size() >= 4) {
      lb_top4_name.setText(reports.get(3).getBrandName());
      lb_top4_value.setText(reports.get(3).getTotal() + " sản phẩm");
    }
  }
  
  public void renderTopProduct() {
  
  }
  
  public void renderBill() {
  
  }
}
