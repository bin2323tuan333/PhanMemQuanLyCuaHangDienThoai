package com.example.controllers.AdminControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeManagementController {

    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtSalary;

    @FXML private TableView<String> employeeTable;
    @FXML private TableColumn<String, String> colId;
    @FXML private TableColumn<String, String> colName;
    @FXML private TableColumn<String, String> colPhone;
    @FXML private TableColumn<String, String> colSalary;

    @FXML
    public void initialize() {
        // dữ liệu giả (test UI ก่อน)
        employeeTable.setItems(FXCollections.observableArrayList(
                "Employee 1",
                "Employee 2",
                "Employee 3"
        ));
    }

    @FXML
    private void handleAdd() {
        System.out.println("Add employee clicked");
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update employee clicked");
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete employee clicked");
    }
}