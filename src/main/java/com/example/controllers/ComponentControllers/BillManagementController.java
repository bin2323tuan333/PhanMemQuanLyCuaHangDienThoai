package com.example.controllers.ComponentControllers;

import com.example.DTO.BillInfo;
import com.example.DTO.RecentBill;
import com.example.controllers.ComponentControllers.Card.BillDetailController;
import com.example.repositories.BillRepository;
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

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillManagementController {
  @FXML
  private DatePicker fromDate;
  @FXML
  private DatePicker toDate;
  @FXML
  private VBox bill_container;
  
  @FXML
  private TextField txt_search;
  @FXML
  private Button btn_search_bill;
  @FXML
  private Button btn_add_bill;
  
  private BillService billService;
  private BillRepository billRepository;
  
  
  @FXML
  public void initialize() {
    billService = new BillService();
    billRepository = new BillRepository();
    setupDatePickers();
    
    List<BillInfo> billInfos = billService.getAllBillInfos();
    renderBills(billInfos);
  }
  
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      List<BillInfo> list = billService.getAllBillInfos();
      renderBills(list);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
  private void setupDatePickers() {
    fromDate.setValue(null);
    toDate.setValue(null);
//    fromDate.setOnAction(event -> filterBills());
//    toDate.setOnAction(event -> filterBills());
  }
  
  
  @FXML
  public void handleBtnSearch() {
    String keyword = txt_search.getText().trim().toLowerCase();
    List<BillInfo> searchResults = new ArrayList<>();
    List<BillInfo> billInfos = billService.getAllBillInfos();
    for (BillInfo bill : billInfos) {
      boolean match = keyword.isEmpty() ||
                              String.valueOf(bill.getBillId()).contains(keyword) ||
                              (bill.getCustomer() != null && bill.getCustomer().getFullName() != null &&
                                       bill.getCustomer().getFullName().toLowerCase().contains(keyword)) ||
                              (bill.getEmployee() != null && bill.getEmployee().getFullName() != null &&
                                       bill.getEmployee().getFullName().toLowerCase().contains(keyword));
      
      if (match) {
        searchResults.add(bill);
      }
    }
    renderBills(searchResults);
  }
  
  private void filterBillsByDate() {
    LocalDate from = fromDate.getValue();
    LocalDate to = toDate.getValue();
    List<BillInfo> filteredList = new ArrayList<>();
    List<BillInfo> billInfos = billService.getAllBillInfos();
    for (BillInfo bill : billInfos) {
      if (bill.getInvoiceDate() != null) {
        LocalDate billDate = bill.getInvoiceDate().toLocalDate();
        boolean matchesDate = true;
        
        if (from != null && billDate.isBefore(from)) {
          matchesDate = false;
        }
        if (to != null && billDate.isAfter(to)) {
          matchesDate = false;
        }
        
        if (matchesDate) {
          filteredList.add(bill);
        }
      }
    }
    renderBills(filteredList);
  }
  
  private void renderBills(List<BillInfo> bills) {
    bill_container.getChildren().clear();
    for (BillInfo bill : bills) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Bill.fxml"));
        VBox card = loader.load();
        BillCardController controller = loader.getController();
        if (controller != null) {
          controller.setData(bill);
        } else {
          System.out.println("Controller NULL!");
        }
        
        card.getStyleClass().add("card");
        bill_container.getChildren().add(card);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}