package com.example.controllers.AdminControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerManagementController {

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    @FXML private TableView<String> customerTable;
    @FXML private TableColumn<String, String> colId;
    @FXML private TableColumn<String, String> colName;
    @FXML private TableColumn<String, String> colPhone;
    @FXML private TableColumn<String, String> colEmail;

    @FXML
    public void initialize() {
        // fake data ก่อน
        customerTable.setItems(FXCollections.observableArrayList(
                "Customer 1",
                "Customer 2",
                "Customer 3"
        ));
    }

    @FXML
    private void handleAdd() {
        System.out.println("Add clicked");
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update clicked");
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete clicked");
    }
}
