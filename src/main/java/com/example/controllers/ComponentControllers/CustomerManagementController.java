package com.example.controllers.ComponentControllers;

import com.example.DTO.CustomerInfo;
import com.example.controllers.ComponentControllers.Card.CustomerCardController;
import com.example.controllers.ComponentControllers.Form.CustomerFormController;
import com.example.services.CustomerService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CustomerManagementController {
  @FXML
  private VBox customer_container;
  @FXML
  private TextField txt_search;
  
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> list = customerService.getAllCustomerInfos();
    render(list);
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/CustomerForm.fxml"));
      Parent root = loader.load();
      CustomerFormController customerFormController = loader.getController();
      customerFormController.setCustomerInfo(null);
      customerFormController.setReload(this::reload);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> list = customerService.getAllCustomerInfos();
    render(list);
  }
  
  public void handleBtnSearch() {
    String s = txt_search.getText().trim().toLowerCase();
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> list = customerService.searchCustomerInfos(s);
    render(list);
  }
  
  
  public void render(List<CustomerInfo> list) {
    this.customer_container.getChildren().clear();
    try {
      for (CustomerInfo item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Customer.fxml"));
        Node node = loader.load();
        CustomerCardController controller = loader.getController();
        controller.setReload(this::reload);
        controller.setCustomerInfo(item);
        this.customer_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void reload() {
    this.txt_search.setText("");
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> list = customerService.getAllCustomerInfos();
    render(list);
  }
}
