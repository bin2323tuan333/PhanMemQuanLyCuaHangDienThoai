package com.example.controllers.EmployeeControllers;

import com.example.models.Product;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class EmployeeSaleController {
  @FXML
  private VBox employee_sale;
  
  @FXML
  public void initialize() {
    try {
      setup();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void setup() throws IOException {
    FXMLLoader createBill = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
    Node createBillNode = createBill.load();
    VBox.setVgrow(createBillNode, Priority.ALWAYS);
    this.employee_sale.getChildren().add(createBillNode);
  }
}
