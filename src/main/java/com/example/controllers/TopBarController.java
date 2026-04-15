package com.example.controllers;

import com.example.models.Employee;
import com.example.services.EmployeeService;
import com.example.services.IAuthService;
import com.example.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TopBarController {
  @FXML
  private Label lb_name;
  @FXML
  private HBox userBox;
  @FXML
  private Label lb_title;
  private MainController mainController;
  
  IAuthService loginService = new AuthService();
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    EmployeeService eService = new EmployeeService();
    int id = mainController.getAccountId();
    Employee emp = eService.getEmployeeByID(id);
    if (emp != null) {
      lb_name.setText(emp.getFullName());
    } else {
      lb_name.setText("Unknown User");
    }
    setTitle("Trang Chủ");
  }
  
  
  public void setTitle(String text) {
    this.lb_title.setText(text);
  }
  
  
  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  }
  
  @FXML
  private void handleToggleMenu() {
    if (mainController != null) {
      mainController.toggleSidebar();
    } else {
      System.out.println("Lỗi: mainController chưa được set!");
    }
  }
}
