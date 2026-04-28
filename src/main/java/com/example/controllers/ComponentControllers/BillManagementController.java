package com.example.controllers.AdminControllers;

import com.example.DTO.RecentBill;
import com.example.services.BillService;
import com.example.controllers.ComponentControllers.Card.BillCardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillManagementController {
  
  @FXML
  private ComboBox<String> statusCombo;
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private FlowPane billContainer;
  
  @FXML
  private Button btn_search;
  @FXML
  private Button btn_reset;
  @FXML
  private Button btn_viewDetail;
  @FXML
  private Button btn_delete;
  @FXML
  private Button btn_add;
  
  @FXML
  private Label lb_totalAmount;
  @FXML
  private Label lb_totalBills;
  
  private BillService billService;
  private ObservableList<RecentBill> billList;
  
  private VBox selectedCard = null;
  
  @FXML
  public void initialize() {
    billService = new BillService();
    setupComboBox();
    setupDatePickers();
    loadBillData();
    billContainer.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
    );
  }
  
  private RecentBill selectedBill;
  
  private void handleCardClick(RecentBill bill) {
    selectedBill = bill;
  }
  
  
  public void handleDelete() {
    if (selectedBill == null) {
      showAlert("Thông báo", "Vui lòng chọn hóa đơn!");
      return;
    }
    
    try {
      billService.deleteBill(selectedBill.getBillId());
      loadBillData();
      showAlert("Thành công", "Đã xóa!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void handleAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      
      // Sau khi đóng cửa sổ thêm, reload lại dữ liệu
      loadBillData();
    } catch (Exception e) {
      showAlert("Lỗi", "Không thể mở cửa sổ thêm hóa đơn: " + e.getMessage());
    }
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
    
    List<RecentBill> bills = billService.getAllBills();
    billList = FXCollections.observableArrayList(bills);
    
    billContainer.getChildren().clear();
    selectedCard = null;
    for (RecentBill bill : billList) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Card/Bill.fxml"));
        VBox card = loader.load();
        BillCardController controller = loader.getController();
        
        
        if (controller != null) {
          controller.setData(bill);
        } else {
          System.out.println("Controller NULL!");
        }
        
        card.getStyleClass().add("card");
        card.setOnMouseClicked(e -> {
          handleCardClick(bill);
          
          if (selectedCard != null) {
            selectedCard.getStyleClass().remove("card-selected");
          }
          
          selectedCard = card;
          selectedCard.getStyleClass().add("card-selected");
        });
        billContainer.getChildren().add(card);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    updateSummary(billList);
  }
  
  private void filterBills() {
    String selectedStatus = statusCombo.getValue();
    
    List<RecentBill> filtered = new ArrayList<>();
    
    for (RecentBill bill : billList) {
      if (selectedStatus.equals("Tất cả") || bill.getStatus().equals(selectedStatus)) {
        filtered.add(bill);
      }
    }
    
    // render lại card
    billContainer.getChildren().clear();
    
    for (RecentBill bill : filtered) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Card/Bill.fxml"));
        VBox card = loader.load();
        BillCardController controller = loader.getController();
        if (controller != null) {
          controller.setData(bill);
        }
        
        card.getStyleClass().add("card");
        card.setOnMouseClicked(e -> {
          handleCardClick(bill);
          
          if (selectedCard != null) {
            selectedCard.getStyleClass().remove("card-selected");
          }
          
          selectedCard = card;
          selectedCard.getStyleClass().add("card-selected");
        });
        billContainer.getChildren().add(card);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    updateSummary(FXCollections.observableArrayList(filtered));
  }
  
  private void updateSummary() {
    updateSummary(billList);
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
    selectedBill = null;
    loadBillData();
    updateSummary();
  }
  
  @FXML
  public void handleViewDetail() {
    if (selectedBill == null) {
      showAlert("Thông báo", "Vui lòng chọn hóa đơn!");
      return;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    showAlert("Chi tiết",
            "ID: " + selectedBill.getBillId() +
                    "\nKhách: " + selectedBill.getCustomerName() +
                    "\nNhân viên: " + selectedBill.getEmployeeName() +
                    "\nTiền: " + selectedBill.getTotal());
  }
  
  private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
  
}