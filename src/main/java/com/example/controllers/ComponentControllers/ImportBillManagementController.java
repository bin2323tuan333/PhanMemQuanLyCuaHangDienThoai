package com.example.controllers.ComponentControllers;

import com.example.DTO.BillInfo;
import com.example.DTO.ImportBillInfo;
import com.example.controllers.ComponentControllers.Card.ImportBillCardController;
import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ImportBillManagementController {
  @FXML
  private TextField txt_search;
  @FXML
  private ComboBox<String> cbb_supplier;
  @FXML
  private ComboBox<String> cbb_price;
  @FXML
  private FlowPane import_bill_container;
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    BillService billService = new BillService();
    List<ImportBillInfo> list = billService.getAllImportBillInfo();
    try {
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/ImportBill.fxml"));
        Node node = loader.load();
        ImportBillCardController controller = loader.getController();
        controller.setImportBillInfo(item);
        this.import_bill_container.getChildren().add(node);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateImportBill.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setTitle("Thêm hóa đơn mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSearch() {
    String key = txt_search.getText().trim().toLowerCase();
    this.import_bill_container.getChildren().clear();
    BillService billService = new BillService();
    List<ImportBillInfo> list = billService.searchImportBills(key);
    try {
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/ImportBill.fxml"));
        Node node = loader.load();
        ImportBillCardController controller = loader.getController();
        controller.setImportBillInfo(item);
        this.import_bill_container.getChildren().add(node);

  }
} catch (IOException e) {
        throw new RuntimeException(e);
    }
  }}
