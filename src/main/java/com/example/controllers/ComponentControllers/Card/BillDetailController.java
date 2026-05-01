package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.RecentBill;
import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class BillDetailController {


    @FXML
    private javafx.scene.control.Button btn_update;
    @FXML
    private javafx.scene.control.Button btn_cancel;
    @FXML
    private javafx.scene.control.Button btn_delete;
    @FXML
    private javafx.scene.control.TextField txt_id;
    @FXML
    private javafx.scene.control.TextField txt_name_customer;
    @FXML
    private javafx.scene.control.TextField txt_name_employee;
    @FXML
    private javafx.scene.control.TextField txt_total_price;
    @FXML
    private VBox bill_detail_container;


    private RecentBill bill;
    private BillService billService = new BillService();

    public void setData(RecentBill bill) {
        this.bill = bill;
        txt_id.setText("🧾 #" + bill.getBillId());
        txt_name_customer.setText("👤 " + bill.getCustomerName());
        txt_name_employee.setText("👨‍💼 " + bill.getEmployeeName());
        txt_total_price.setText("💰 " + String.format("%,.0f VNĐ", bill.getTotal()));

    }
    @FXML
    private void handleEdit() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chức năng đang phát triển");
        alert.setHeaderText(null);
        alert.setContentText("Chức năng chỉnh sửa hóa đơn đang được phát triển. Vui lòng quay lại sau!");
        alert.showAndWait();

    }
        @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc muốn xóa hóa đơn #" + bill.getBillId() + "?");



}
        @FXML
    private void handleCancel() {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();}

}
