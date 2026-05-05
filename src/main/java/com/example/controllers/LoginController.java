package com.example.controllers;

import com.example.DTO.EmployeeInfo;
import com.example.models.Account;
import com.example.services.AuthService;
import com.example.services.EmployeeService;
import com.example.utils.AppSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
  @FXML
  private Label label_title;
  @FXML
  private TextField textField_username;
  @FXML
  private PasswordField textField_password;
  @FXML
  private Button button_login;
  @FXML
  private Label label_message;
  
  
  @FXML
  public void button_login_click() {
    String user = textField_username.getText().trim();
    String pass = textField_password.getText();
    
    if (user.isEmpty() || pass.isEmpty()) {
      label_message.setText("Không được để trống!");
      label_message.setStyle("-fx-text-fill: red;");
      return;
    }
    
    AuthService authService = new AuthService();
    Account acc = authService.login(user, pass);
    
    if (acc != null) {
      AppSection.Instance().setAccount(acc);
      loadMainScreen();
    } else {
      label_message.setText("Sai tài khoản hoặc mật khẩu!");
      label_message.setStyle("-fx-text-fill: red;");
    }
  }
  
  private void loadMainScreen() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/MainContainer.fxml"));
      Parent root = loader.load();
      MainController mainController = loader.getController();
      mainController.setup();
      Stage stage = (Stage) label_message.getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.centerOnScreen();
      stage.show();
      
    } catch (IOException e) {
      e.printStackTrace();
      label_message.setText("Lỗi load file giao diện!");
      label_message.setStyle("-fx-text-fill: red;");
    }
  }
}