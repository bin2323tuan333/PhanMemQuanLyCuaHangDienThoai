package com.example.controllers.ComponentControllers.Form;

import com.example.models.Brand;
import com.example.services.BrandService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BrandFormController {
  @FXML
  private Button btn_add;
  @FXML
  private Button btn_update;
  @FXML
  private Button btn_cancel;
  @FXML
  private Button btn_delete;
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name;
  
  private Brand brand;
  
  public void setBrand(Brand brand) {
    this.brand = brand;
    setup();
  }
  
  @FXML
  public void initialize() {
  }
  
  public void setup() {
    if (this.brand != null) {
      this.txt_id.setText(String.valueOf(this.brand.getBrandId()));
      this.txt_name.setText(this.brand.getBrandName());
    }
    
    if (this.brand == null) {
      if (btn_delete != null) {
        btn_delete.setVisible(false);
        btn_delete.setManaged(false);
      }
      if (btn_update != null) {
        btn_update.setVisible(false);
        btn_update.setManaged(false);
      }
    } else {
      if (btn_add != null) {
        btn_add.setVisible(false);
        btn_add.setManaged(false);
      }
    }
  }
  
  public void handleBtnAdd() {
    BrandService brandService = new BrandService();
    Brand newBrand = new Brand();
    newBrand.setBrandName(txt_name.getText());
    brandService.insertBrand(newBrand);
    closeForm();
  }
  
  public void handleBtnUpdate() {
    BrandService brandService = new BrandService();
    this.brand.setBrandName(txt_name.getText());
    brandService.updateBrand(this.brand);
    closeForm();
  }
  
  public void handleBtnDelete() {
      BrandService brandService = null;
      if (brandService.hasProduct(this.brand.getBrandId())) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("Cannot Delete Brand");
          alert.setHeaderText(null);
          alert.setContentText("Brand không thể xóa vì có sản phẩm liên quan.");
          alert.showAndWait();
          return;
      }
      brandService = new BrandService();
      brandService.deleteBrand(this.brand.getBrandId());
      closeForm();
  }
  
  public void handleBtnCancel() {
    closeForm();
  }
  
  public void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    if (stage != null) {
      stage.close();
    }
  }
}
