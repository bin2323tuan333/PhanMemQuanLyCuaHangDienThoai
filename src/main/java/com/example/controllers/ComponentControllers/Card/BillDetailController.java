package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.RecentBill;
import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;

public class BillDetailController {
    @FXML
    private Label lb_id;
    @FXML
    private Label lb_customer;
    @FXML
    private Label lb_employee;
    @FXML
    private Label lb_total;
    @FXML
    private Label lb_date;

    private RecentBill bill;
    private BillService billService = new BillService();

    public void setData(RecentBill bill) {
        this.bill = bill;
        lb_id.setText("🧾 #" + bill.getBillId());
        lb_customer.setText("👤 " + bill.getCustomerName());
        lb_employee.setText("👨‍💼 " + bill.getEmployeeName());
        lb_total.setText("💰 " + String.format("%,.0f VNĐ", bill.getTotal()));
        lb_date.setText("📅 " + bill.getDate().toString());
    }
    @FXML
    private void handleEdit() {
        System.out.println("Edit bill: " + bill.getBillId());

    }
        @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc muốn xóa hóa đơn #" + bill.getBillId() + "?");



}}
