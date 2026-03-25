package com.example.controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class SideBarController {
    @FXML private Button btn_dashboard;
    @FXML private Button btn_product;
    @FXML private Button btn_customer;
    @FXML private Button btn_bill;
    @FXML private Button btn_employee;
    @FXML private Button btn_analytics;
    @FXML private Button btn_setting;
    @FXML public static ScrollPane contentArea;
    private Button currentActiveButton;


    @FXML
    public void initialize() {
        currentActiveButton = btn_dashboard;
        btn_dashboard.getStyleClass().add("active-menu");
    }
    public void loadDefaultPage() {
        loadPage("/com/example/admin/DashBoard.fxml");
    }
    @FXML
    public void handle_menu_click(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (currentActiveButton != null) {
            currentActiveButton.getStyleClass().remove("active-menu");
        }
        clickedButton.getStyleClass().add("active-menu");
        currentActiveButton = clickedButton;

        String buttonId = clickedButton.getId();
        switch (buttonId) {
            case "btn_dashboard":
                loadPage("/com/example/admin/DashBoard.fxml");
                break;
            case "btn_product":
                loadPage("/com/example/admin/ProductManagement.fxml");
                break;
            case "btn_analytics":
                loadPage("/com/example/admin/Analytics.fxml");
                break;
        }
    }

    private void loadPage(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            contentArea.setContent(root);
            contentArea.setFitToWidth(true);
            contentArea.setVvalue(0);

        } catch (IOException e) {
            System.err.println("Lỗi load trang: " + fxmlPath);
        }
    }
}
