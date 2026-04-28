package com.example.controllers.ComponentControllers;

import com.example.DTO.RecentBill;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.awt.*;

public class BillCardController {
    @FXML
    private Label lb_id;
    @FXML
    private Label lb_customer;
    @FXML
    private Label lb_employee;
    @FXML
    private Label lb_total;
    @FXML
    private Label lb_status;

    public void setData(RecentBill bill) {
        lb_id.setText("🧾 #" + bill.getBillId());
        lb_customer.setText("👤 " + bill.getCustomerName());
        lb_employee.setText("👨‍💼 " + bill.getEmployeeName());
        lb_total.setText("💰 " + String.format("%,.0f VNĐ", bill.getTotal()));
        lb_status.setText("📌 " + bill.getStatus());


        switch (bill.getStatus()) {
            case "COMPLETED" -> lb_status.setStyle("-fx-text-fill: green;");
            case "PENDING" -> lb_status.setStyle("-fx-text-fill: orange;");
            case "CANCELLED" -> lb_status.setStyle("-fx-text-fill: red;");
        }

}}
