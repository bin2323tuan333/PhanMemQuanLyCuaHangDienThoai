package com.example.controllers.ComponentControllers;

import com.example.DTO.CustomerInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


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
    this.lb_gender.setText(this.customerInfo.isGender() ? "Nữ" : "Nam");
    this.lb_dob.setText(this.customerInfo.getDob().toString());
  }
}
