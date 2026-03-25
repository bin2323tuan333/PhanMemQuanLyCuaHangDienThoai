package com.example.controllers;

import com.example.services.ILoginService;
import com.example.services.TempLoginService;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;

public class TopBarController {
    @FXML Label lb_name;
    @FXML private HBox userBox;
    private MainController mainController;
    ILoginService loginService = new TempLoginService();

    @FXML
    public void initialize() {
        if (loginService.getCurrentUser() != null) {
            lb_name.setText(loginService.getCurrentUser().getUsername());
        }
        // 2. Tạo Menu
        ContextMenu userMenu = new ContextMenu();
        userMenu.getStyleClass().add("user-context-menu");
        MenuItem changePassItem = new MenuItem("Đổi mật khẩu");
        MenuItem logoutItem = new MenuItem("Đăng xuất");

        logoutItem.getStyleClass().add("logout-item");
        changePassItem.setStyle("-fx-text-fill: black;");
        logoutItem.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        userMenu.getItems().addAll(changePassItem, new SeparatorMenuItem(), logoutItem);
        userBox.setOnMouseClicked(event -> {
            if (userMenu.isShowing()) {
                userMenu.hide();
            } else {
                userMenu.show(userBox, Side.BOTTOM, 0, 5);
            }
        });

        logoutItem.setOnAction(e -> handleLogout());
        changePassItem.setOnAction(e -> handleChangePass());
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
    private void handleLogout() {
        System.out.println("Đang đăng xuất...");
    }

    private void handleChangePass() {
        System.out.println("Mở form đổi mật khẩu...");
    }
}
