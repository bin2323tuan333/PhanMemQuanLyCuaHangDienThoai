package com.example.controllers.AdminControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductManagementController {

    @FXML private TextField txtName;
    @FXML private TextField txtCategory;
    @FXML private TextField txtPrice;
    @FXML private TextField txtStock;

    @FXML private TableView<String> productTable;
    @FXML private TableColumn<String, String> colId;
    @FXML private TableColumn<String, String> colName;
    @FXML private TableColumn<String, String> colCategory;
    @FXML private TableColumn<String, String> colPrice;
    @FXML private TableColumn<String, String> colStock;

    @FXML
    public void initialize() {
        productTable.setItems(FXCollections.observableArrayList(
                "iPhone 15 Pro Max",
                "Samsung Galaxy S24",
                "MacBook Air M2"
        ));
    }

    @FXML
    private void handleAdd() {
        System.out.println("Add product clicked");
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update product clicked");
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete product clicked");
    }
}