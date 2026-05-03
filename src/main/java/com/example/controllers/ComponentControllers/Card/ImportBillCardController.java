package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.ImportBillInfo;
import com.example.controllers.ComponentControllers.EmployeeFormController;
import com.example.controllers.ComponentControllers.ImportBillFormController;
import com.example.controllers.ComponentControllers.ImportBillManagementController;
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
  private Label lb_supplier;
  @FXML
  private Label lb_employee;
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
    this.lb_id.setText(this.importBillInfo.getImportId() + "");
    this.lb_employee.setText(this.importBillInfo.getEmployee().getFullName() + "");
    this.lb_supplier.setText(this.importBillInfo.getSupplier().getName() + "");
    this.lb_total.setText(this.importBillInfo.getTotalAmount() + "");
  }
  
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ImportBillForm.fxml"));
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
  
}
