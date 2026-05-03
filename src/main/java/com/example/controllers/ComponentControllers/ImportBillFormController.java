package com.example.controllers.ComponentControllers;

import com.example.DTO.ImportBillInfo;
import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ImportBillFormController {
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name_supplier;
  @FXML
  private TextField txt_total_price;
  @FXML
  private TextField txt_name_employee;
  @FXML
    private javafx.scene.control.Button btn_cancel;
  
  
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
  
  public void handleBtnUpdate() throws SQLException {

    BillService importBillService = new BillService();

  importBillService.updateImportBill(getImportBillformfromInput());
close();

  }
  
  public void handleBtnDelete() {
    BillService importBillService = new BillService();
    importBillService.deleteImportBill(importBillInfo.getImportId());
  close();
  }
  
  public void handleBtnCancel() {
    close();
  }
  public ImportBillInfo getImportBillformfromInput() {
    ImportBillInfo i = new ImportBillInfo();
    i.setImportId(Integer.parseInt(txt_id.getText()));
    i.setEmployee(importBillInfo.getEmployee());
    i.setSupplier(importBillInfo.getSupplier());
    i.setTotalAmount(Double.parseDouble(txt_total_price.getText()));
    return i;
  }
  public void handleBtnAdd() throws SQLException {
    BillService importBillService = new BillService();
    importBillService.addImportBill(getImportBillformfromInput());
  }
  public void close() {
    Stage stage = (Stage) btn_cancel.getScene().getWindow();
    stage.close();

  }
}
