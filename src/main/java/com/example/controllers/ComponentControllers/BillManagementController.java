package com.example.controllers.ComponentControllers;

import com.example.DTO.RecentBill;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillManagementController {
  
  @FXML
  private ComboBox<String> cbb_status;
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
  private ObservableList<RecentBill> billList;
private BillRepository billRepository;
  
  
  @FXML
  public void initialize() {
    billService = new BillService();
    billRepository = new BillRepository();
    setupComboBox();
    setupDatePickers();
    loadBillData();
    bill_container.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
    );
  }
  
  private RecentBill selectedBill;
  
  private void handleCardClick(RecentBill bill) {
    selectedBill = bill;
  }
  
  
  public void handleAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      
      loadBillData();
    } catch (Exception e) {
    }
  }
  
  
  private void setupComboBox() {
    cbb_status.getItems().setAll("Tất cả", "COMPLETED", "PROCESSING", "PENDING", "CANCELLED");
    cbb_status.setValue("Tất cả");
    cbb_status.setOnAction(event -> filterBills());
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
    
    bill_container.getChildren().clear();
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
        });
        
        bill_container.getChildren().add(card);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  private void filterBills() {
    String selectedStatus = cbb_status.getValue();
    
    List<RecentBill> filtered = new ArrayList<>();
    
    for (RecentBill bill : billList) {
      if (selectedStatus.equals("Tất cả") || bill.getStatus().equals(selectedStatus)) {
        filtered.add(bill);
      }
    }
    
    // render lại card
    bill_container.getChildren().clear();
    
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
          
          
        });
        bill_container.getChildren().add(card);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
  }
public  void handleSearch() throws Exception {
    String keyword = txt_search.getText().trim();
    if (keyword.isEmpty()) {
      loadBillData();
      return;
    }

    List<RecentBill> searchResults = billRepository.searchBills(keyword);

    bill_container.getChildren().clear();

    for (RecentBill bill : searchResults) {
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
        });
        bill_container.getChildren().add(card);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  
}