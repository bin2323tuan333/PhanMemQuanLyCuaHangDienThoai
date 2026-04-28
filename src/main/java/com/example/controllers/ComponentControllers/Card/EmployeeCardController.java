package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.EmployeeInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class EmployeeCardController {
  @FXML
  private HBox employee_card;
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_id;
  @FXML
  private Label lb_address;
  @FXML
  private Label lb_role;
  @FXML
  private Label lb_dob;
  @FXML
  private Label lb_status;
  
  private EmployeeInfo employeeInfo;
  
  public void setEmployeeInfo(EmployeeInfo employeeInfo) {
    this.employeeInfo = employeeInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
  }
  
  private void setup() {
    this.lb_name.setText(employeeInfo.getFullName());
    this.lb_dob.setText(employeeInfo.getBirthday().toString());
    this.lb_id.setText("ID: " + employeeInfo.getEmployeeId());
    this.lb_address.setText(employeeInfo.getAddress());
    this.lb_role.setText(employeeInfo.getRoleName());
    this.lb_phone.setText(employeeInfo.getPhoneNumber());
    this.lb_status.setText((employeeInfo.getStatus() ? "Hoạt động" : "Khóa"));
  }
  
  public void handleClick() {
    System.out.println("In ra cái chi tiết nhân viên mày!");
  }
  
}
