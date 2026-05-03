package com.example.controllers.ComponentControllers;

import com.example.DTO.ImportBillInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ImportBillFormController {
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name_supplier;
  @FXML
  private TextField txt_total_price;
  @FXML
  private TextField txt_name_employee;
  
  
  private ImportBillInfo importBillInfo;
  
  public void setImportBillInfo(ImportBillInfo importBillInfo) {
    this.importBillInfo = importBillInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  
  public void setup() {
    if (this.importBillInfo != null) {
      txt_id.setText(this.importBillInfo.getImportId() + "");
      txt_name_employee.setText(this.importBillInfo.getEmployee().getFullName() + "");
      txt_name_supplier.setText(this.importBillInfo.getSupplier().getName() + "");
      txt_total_price.setText(this.importBillInfo.getTotalAmount() + "");
    }
  }
  
  public void handleBtnUpdate() {
  
  }
  
  public void handleBtnDelete() {
  
  }
  
  public void handleBtnCancel() {
  
  }
}
