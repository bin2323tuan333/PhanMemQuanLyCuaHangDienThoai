package com.example.controllers.AdminControllers;

import com.example.DTO.RecentBill;
import com.example.services.BillService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BillManagementController {

    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private TableView<RecentBill> billTable;

    // Khai báo các cột trong TableView
    @FXML
    private TableColumn<RecentBill, Integer> col_billId;
    @FXML
    private TableColumn<RecentBill, String> col_customerName;
    @FXML
    private TableColumn<RecentBill, String> col_invoiceDate;
    @FXML
    private TableColumn<RecentBill, Double> col_totalAmount;
    @FXML
    private TableColumn<RecentBill, String> col_employeeName;  // Thêm cột nhân viên
    @FXML
    private TableColumn<RecentBill, String> col_status;        // Thêm cột trạng thái

    @FXML
    private Button btn_search;
    @FXML
    private Button btn_reset;
    @FXML
    private Button btn_viewDetail;
    @FXML
    private Label lb_totalAmount;
    @FXML
    private Label lb_totalBills;

    private BillService billService;
    private ObservableList<RecentBill> billList;

    @FXML
    public void initialize() {
        billService = new BillService();
        setupTable();
        setupComboBox();
        setupDatePickers();
        loadBillData();
    }

    private void setupTable() {
        // Gán dữ liệu cho các cột
        col_billId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        col_customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        // Xử lý cột date từ Date thành String để hiển thị
        col_invoiceDate.setCellValueFactory(cellData -> {
            RecentBill bill = cellData.getValue();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return new javafx.beans.property.SimpleStringProperty(sdf.format(bill.getDate()));
        });

        col_totalAmount.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Định dạng cột số tiền
        col_totalAmount.setCellFactory(column -> new TableCell<RecentBill, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f VNĐ", item));
                }
            }
        });

        // Định dạng cột trạng thái với màu sắc
        col_status.setCellFactory(column -> new TableCell<RecentBill, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item) {
                        case "COMPLETED":
                            setStyle("-fx-text-fill: #10b981; -fx-font-weight: bold;");
                            break;
                        case "PROCESSING":
                            setStyle("-fx-text-fill: #3b82f6; -fx-font-weight: bold;");
                            break;
                        case "PENDING":
                            setStyle("-fx-text-fill: #f59e0b; -fx-font-weight: bold;");
                            break;
                        case "CANCELLED":
                            setStyle("-fx-text-fill: #ef4444; -fx-font-weight: bold;");
                            break;
                        default:
                            setStyle("-fx-text-fill: #64748b;");
                    }
                }
            }
        });
    }


    private void setupComboBox() {
        statusCombo.getItems().setAll("Tất cả", "COMPLETED", "PROCESSING", "PENDING", "CANCELLED");
        statusCombo.setValue("Tất cả");
        statusCombo.setOnAction(event -> filterBills());
    }

    private void setupDatePickers() {
        fromDate.setValue(null);
        toDate.setValue(null);
        fromDate.setOnAction(event -> filterBills());
        toDate.setOnAction(event -> filterBills());
    }

    private void loadBillData() {
        try {
            List<RecentBill> bills = billService.getAllBills();
            billList = FXCollections.observableArrayList(bills);
            billTable.setItems(billList);
            updateSummary();
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải dữ liệu hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void filterBills() {
        String selectedStatus = statusCombo.getValue();
        LocalDate startDate = fromDate.getValue();
        LocalDate endDate = toDate.getValue();

        ObservableList<RecentBill> filteredList = FXCollections.observableArrayList();

        for (RecentBill bill : billList) {
            boolean matchStatus = true;

            if (selectedStatus != null && !selectedStatus.equals("Tất cả")) {
                matchStatus = bill.getStatus() != null && bill.getStatus().equals(selectedStatus);
            }

            // Lọc theo khoảng ngày (Date -> LocalDate)
            boolean matchDate = true;
            if (startDate != null || endDate != null) {
                if (bill.getDate() != null) {
                    LocalDate billDate = new java.sql.Date(bill.getDate().getTime()).toLocalDate();

                    if (startDate != null && endDate != null) {
                        matchDate = !billDate.isBefore(startDate) && !billDate.isAfter(endDate);
                    } else if (startDate != null) {
                        matchDate = !billDate.isBefore(startDate);
                    } else if (endDate != null) {
                        matchDate = !billDate.isAfter(endDate);
                    }
                } else {
                    matchDate = false;
                }
            }

            if (matchStatus && matchDate) {
                filteredList.add(bill);
            }
        }

        billTable.setItems(filteredList);
        updateSummary(filteredList);
    }

    private void updateSummary() {
        updateSummary(billTable.getItems());
    }

    private void updateSummary(ObservableList<RecentBill> bills) {
        double total = 0;
        for (RecentBill bill : bills) {
            total += bill.getTotal();
        }
        lb_totalBills.setText("Tổng số hóa đơn: " + bills.size());
        lb_totalAmount.setText(String.format("Tổng tiền: %,.0f VNĐ", total));
    }

    @FXML
    public void handleSearch() {
        filterBills();
    }

    @FXML
    public void handleReset() {
        statusCombo.setValue("Tất cả");
        fromDate.setValue(null);
        toDate.setValue(null);
        billTable.setItems(billList);
        updateSummary();
    }

    @FXML
    public void handleViewDetail() {
        RecentBill selectedBill = billTable.getSelectionModel().getSelectedItem();
        if (selectedBill == null) {
            showAlert("Thông báo", "Vui lòng chọn hóa đơn để xem chi tiết!");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateStr = sdf.format(selectedBill.getDate());

        showAlert("Chi tiết hóa đơn",
                "📋 Mã hóa đơn: #" + selectedBill.getBillId() +
                        "\n👤 Khách hàng: " + selectedBill.getCustomerName() +
                        "\n👨‍💼 Nhân viên: " + selectedBill.getEmployeeName() +
                        "\n💰 Tổng tiền: " + String.format("%,.0f VNĐ", selectedBill.getTotal()) +
                        "\n📅 Ngày tạo: " + dateStr +
                        "\n📌 Trạng thái: " + selectedBill.getStatus());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}