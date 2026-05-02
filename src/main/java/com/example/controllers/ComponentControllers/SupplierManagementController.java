package com.example.controllers.ComponentControllers;

import com.example.controllers.ComponentControllers.Card.SupplierCardController;
import com.example.models.Supplier;
import com.example.services.SupplierService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SupplierManagementController {
  @FXML
  private TextField txt_search;
  @FXML
  private VBox supplier_container;
  
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    SupplierService supplierService = new SupplierService();
    List<Supplier> suppliers = supplierService.getAllSuppliers();
    renderSupplier(suppliers);
  }
  
  @FXML
  public void handleBtnSearch() {
    String s = txt_search.getText().trim().toLowerCase();
    List<Supplier> list = new ArrayList<>();
    
    SupplierService supplierService = new SupplierService();
    List<Supplier> suppliers = supplierService.getAllSuppliers();
    
    for (var item : suppliers) {
      if (item.getName().trim().toLowerCase().contains(s) ||
                  item.getPhone().trim().toLowerCase().contains(s)) {
        list.add(item);
      }
    }
    
    renderSupplier(list);
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/SupplierForm.fxml"));
      Parent root = loader.load();
      SupplierFormController controller = loader.getController();
      controller.setSupplier(null);
      Stage stage = new Stage();
      stage.setTitle("Thêm Nhà Cung Cấp Mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void renderSupplier(List<Supplier> list) {
    try {
      this.supplier_container.getChildren().clear();
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Supplier.fxml"));
        Node node = loader.load();
        SupplierCardController controller = loader.getController();
        controller.setSupplier(item);
        this.supplier_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


