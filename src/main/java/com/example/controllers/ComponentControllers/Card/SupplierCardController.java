package com.example.controllers.ComponentControllers.Card;

import com.example.controllers.ComponentControllers.Form.SupplierFormController;
import com.example.models.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class SupplierCardController {
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_email;
  @FXML
  private Label lb_address;
  
  private Supplier supplier;
  private Runnable reload;
  
  public void setReload(Runnable reload) {
    this.reload = reload;
  }
  
  public void setSupplier(Supplier supplier) {
    this.supplier = supplier;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.lb_name.setText(supplier.getName());
    this.lb_phone.setText(supplier.getPhone());
    this.lb_email.setText(supplier.getEmail());
    this.lb_address.setText(supplier.getAddress());
  }
  
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/SupplierForm.fxml"));
      Parent root = loader.load();
      SupplierFormController controller = loader.getController();
      controller.setSupplier(this.supplier);
      controller.setReload(reload);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
