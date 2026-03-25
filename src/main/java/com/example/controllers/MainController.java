package com.example.controllers;

import com.example.controllers.AdminControllers.SideBarController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {
    @FXML private TopBarController topBarController;
    @FXML private VBox sideBar;
    @FXML private ScrollPane mainScrollPane;
    @FXML private SideBarController sideBarController;

    private boolean isExpanded = false;
    private final double expandedWidth = 200;
    private final double collapsedWidth = 0;

    @FXML
    public void initialize() {
        SideBarController.contentArea = mainScrollPane;
        if (sideBarController != null) {
            sideBarController.loadDefaultPage();
        }
        topBarController.setMainController(this);
        sideBar.setPrefWidth(collapsedWidth);
        sideBar.setVisible(false);
        sideBar.setManaged(false);
    }

    @FXML
    public void toggleSidebar() {
        double targetWidth = isExpanded ? collapsedWidth : expandedWidth;
        KeyValue keyValue = new KeyValue(sideBar.prefWidthProperty(), targetWidth);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> {
            if (!isExpanded) {
                sideBar.setVisible(false);
                sideBar.setManaged(false);
            }
        });
        if (!isExpanded) {
            sideBar.setVisible(true);
            sideBar.setManaged(true);
        }
        timeline.play();
        isExpanded = !isExpanded;
    }
}
