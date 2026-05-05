package com.example.controllers.ComponentControllers.Card;

import com.example.controllers.ComponentControllers.Form.BrandFormController;
import com.example.models.Brand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BrandCardController {
  @FXML
  private Label lb_name;
  
  private Brand brand;
  
  public void setBrand(Brand brand) {
    this.brand = brand;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.lb_name.setText(this.brand.getBrandName());
  }
  
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/BrandForm.fxml"));
      Parent root = loader.load();
      BrandFormController controller = loader.getController();
      controller.setBrand(this.brand);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
