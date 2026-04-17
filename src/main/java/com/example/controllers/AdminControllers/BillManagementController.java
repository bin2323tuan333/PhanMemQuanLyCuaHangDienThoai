package com.example.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class BillManagementController {

    @FXML private ComboBox<String> statusCombo;
    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;
    @FXML private TableView<?> billTable;

    public void initialize() {
        // Gán danh sách trạng thái cho ComboBox
        statusCombo.getItems().setAll("Tất cả", "Đã thanh toán", "Chờ thanh toán", "Quá hạn");
        // Bạn có thể chọn giá trị mặc định
        statusCombo.setValue("Tất cả");
    }
}