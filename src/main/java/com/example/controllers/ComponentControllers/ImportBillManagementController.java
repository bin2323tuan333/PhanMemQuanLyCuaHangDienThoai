package com.example.controllers.ComponentControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

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
  
  }
  
  public void setup() {
  
  }
}
