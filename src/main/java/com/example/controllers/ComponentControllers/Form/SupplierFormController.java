package com.example.controllers.ComponentControllers.Form;

import com.example.models.Supplier;
import com.example.services.SupplierService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupplierFormController {
  @FXML
  private Button btn_add;
  @FXML
  private Button btn_update;
  @FXML
  private Button btn_delete;
  @FXML
  private Button btn_cancel;
  
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name;
  @FXML
  private TextField txt_phone;
  @FXML
  private TextField txt_email;
  @FXML
  private TextField txt_address;
  
  private Supplier supplier;
  
  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
    setup();
  }
  
  @FXML
  public void initialize() {
  }
  
  public void setup() {
    if (this.supplier != null) {
      txt_id.setText(String.valueOf(supplier.getSupplierId()));
      txt_name.setText(supplier.getName());
      txt_address.setText(supplier.getAddress());
      txt_email.setText(supplier.getEmail());
      txt_phone.setText(supplier.getPhone());
    }
    if (this.supplier == null) {
      btn_delete.setVisible(false);
      btn_delete.setManaged(false);
      btn_update.setVisible(false);
      btn_update.setManaged(false);
    } else {
      btn_add.setVisible(false);
      btn_add.setManaged(false);
    }
  }
  
  
  public void handleBtnAdd() {
    SupplierService supplierService = new SupplierService();
    Supplier newSupplier = getSupplierDataFromForm();
    supplierService.addSupplier(newSupplier);
    closeForm();
  }
  
  public void handleBtnUpdate() {
    SupplierService supplierService = new SupplierService();
    Supplier s = getSupplierDataFromForm();
    supplierService.updateSupplier(s);
    closeForm();
  }
  
  public void handleBtnDelete() {
    SupplierService supplierService = new SupplierService();
    supplierService.deleteSupplier(supplier.getSupplierId());
    closeForm();
  }
  
  
  public void handleBtnCancel() {
    closeForm();
  }
  
  private Supplier getSupplierDataFromForm() {
    try {
      Supplier sup = new Supplier();
      if (this.supplier != null) {
        sup.setSupplierId(this.supplier.getSupplierId());
      }
      sup.setName(txt_name.getText().trim());
      sup.setPhone(txt_phone.getText().trim());
      sup.setEmail(txt_email.getText().trim());
      sup.setAddress(txt_address.getText().trim());
      return sup;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    stage.close();
  }
}
