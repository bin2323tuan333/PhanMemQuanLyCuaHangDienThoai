package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.CustomerInfo;
import com.example.controllers.ComponentControllers.CreateBillController;
import com.example.controllers.ComponentControllers.Form.CustomerFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class CustomerCardController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_dob;
  @FXML
  private Label lb_gender;
  @FXML
  private Label lb_address;
  
  private CustomerInfo customerInfo;
  private CreateBillController parent;
  
  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.lb_name.setText(this.customerInfo.getCustomerName());
    String phone = this.customerInfo.getPhone();
    this.lb_phone.setText((phone != null && !phone.isEmpty()) ? phone : "N/A");
    String address = this.customerInfo.getAddress();
    this.lb_address.setText((address != null && !address.isEmpty()) ? address : "Chưa cập nhật");
    this.lb_gender.setText(this.customerInfo.getGender() ? "Nữ" : "Nam");
    this.lb_dob.setText(this.customerInfo.getDob().toString());
  }
  
  public void setParentController(CreateBillController parent) {
    this.parent = parent;
  }
  
  public void handleClick() {
    if (parent != null) {
      parent.selectCustomer(this.customerInfo);
      return;
    }
    
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/CustomerForm.fxml"));
      Parent root = loader.load();
      CustomerFormController customerFormController = loader.getController();
      customerFormController.setCustomerInfo(this.customerInfo);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
}
