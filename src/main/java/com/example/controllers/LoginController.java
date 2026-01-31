package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    // Khai báo các biến tương ứng với fx:id bên file FXML
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    // Hàm xử lý sự kiện khi bấm nút (tương ứng với onAction)
    @FXML
    protected void onLoginButtonClick() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.equals("admin") && pass.equals("123")) {
            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("✅ Đăng nhập thành công!");
        } else {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("❌ Sai tài khoản hoặc mật khẩu!");
        }
    }
}