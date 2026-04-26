package com.example.controllers.ComponentControllers;

import com.example.controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingController {
  @FXML
  private VBox system_container;
  @FXML
  private TextField txt_employee_id;
  @FXML
  private TextField txt_employee_name;
  @FXML
  private DatePicker dp_employee_dob;
  @FXML
  private RadioButton rd_male;
  @FXML
  private RadioButton rd_female;
  @FXML
  private TextField txt_employee_phone;
  @FXML
  private TextField txt_employee_address;
  @FXML
  private TextField txt_shop_name;
  @FXML
  private TextField txt_shop_address;
  @FXML
  private TextField txt_shop_phone;
  @FXML
  private TextField txt_shop_tax;
  
  private MainController mainController;
  
  public void setMainController(MainController main) {
    this.mainController = main;
  }
  
  @FXML
  public void initialize() {
//    hideSystemContainer();
  }
  
  public void setup() {
  
  }
  
  public void hideSystemContainer() {
    this.system_container.setVisible(false);
    this.system_container.setManaged(false);
  }
}
