package com.example.controllers.AdminControllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DashBoardController {
    @FXML
    private VBox sideBar;

    private boolean isSidebarVisible = true;

    @FXML
    void on_menu_click(ActionEvent event) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), sideBar);

        if (sideBar.isVisible()) {
            sideBar.setVisible(false);
            sideBar.setManaged(false);
        } else {
            sideBar.setVisible(true);
            sideBar.setManaged(true);
        }
        transition.play();
    }
}
