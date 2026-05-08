package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.ImportBillInfo;
import com.example.controllers.ComponentControllers.Form.ImportBillFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportBillCardController {
  @FXML
  private Label lb_id;
  @FXML
  private Label lb_supplier_name;
  @FXML
  private Label lb_employee_name;
  @FXML
  private Label lb_supplier_phone;
  @FXML
  private Label lb_employee_phone;
  @FXML
  private Label lb_supplier_email;
  @FXML
  private Label lb_total;
  
  private ImportBillInfo importBillInfo;
  
  public void setImportBillInfo(ImportBillInfo importBillInfo) {
    this.importBillInfo = importBillInfo;
    setup();
  }
  
  @FXML
  public void initalize() {
  
  }
  
  public void setup() {
    this.lb_id.setText("NH_" + this.importBillInfo.getImportId() + "");
    this.lb_employee_name.setText(this.importBillInfo.getEmployee().getFullName() + "");
    this.lb_supplier_name.setText(this.importBillInfo.getSupplier().getName() + "");
    this.lb_employee_phone.setText(this.importBillInfo.getEmployee().getPhoneNumber() + "");
    this.lb_supplier_phone.setText(this.importBillInfo.getSupplier().getPhone() + "");
    this.lb_supplier_email.setText(this.importBillInfo.getSupplier().getEmail() + "");
    this.lb_total.setText("" + String.format("%,.0f VNĐ", this.importBillInfo.getTotalAmount()));
  }
  
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/ImportBillForm.fxml"));
      Parent root = loader.load();
      ImportBillFormController controller = loader.getController();
      controller.setImportBillInfo(this.importBillInfo);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm nhập hàng mới");
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public ImportBillInfo getImportBillInfo() {
    return this.importBillInfo;
  }
  
}
