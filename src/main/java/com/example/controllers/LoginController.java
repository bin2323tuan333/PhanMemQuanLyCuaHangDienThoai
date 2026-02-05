package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    protected void button_login_click() {
        String user = textField_username.getText();
        String pass = textField_password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            label_message.setText("Không được để trống!");
            label_message.setStyle("-fx-text-fill: red;");
            return;
        }

        if (user.equals("admin") && pass.equals("123")) {
            label_message.setText("Đăng nhập thành công!");
            label_message.setStyle("-fx-text-fill: green;");
        } else {
            label_message.setText("Sai tài khoản hoặc mật khẩu!");
            label_message.setStyle("-fx-text-fill: red;");
        }
    }
}