package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TopBarController {
    private MainController mainController;

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
