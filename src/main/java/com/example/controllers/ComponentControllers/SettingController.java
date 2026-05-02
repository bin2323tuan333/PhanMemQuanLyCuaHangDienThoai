package com.example.controllers.ComponentControllers;

import com.example.DTO.EmployeeInfo;
import com.example.DTO.SystemSetting;
import com.example.controllers.MainController;
import com.example.services.SystemService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
  private EmployeeInfo employeeInfo;
  private SystemSetting systemSetting;
  
  
  private ToggleGroup genderGroup;
  
  public void setEmployeeInfo(EmployeeInfo employeeInfo) {
    this.employeeInfo = employeeInfo;
    setup();
  }
  
  public void setMainController(MainController main) {
    this.mainController = main;
  }
  
  @FXML
  public void initialize() {
    genderGroup = new ToggleGroup();
    rd_male.setToggleGroup(genderGroup);
    rd_female.setToggleGroup(genderGroup);
    rd_male.setSelected(true);
  }
  
  public void setup() {
    if (employeeInfo != null) {
      txt_employee_id.setText(String.valueOf(employeeInfo.getEmployeeId()));
      txt_employee_name.setText(employeeInfo.getFullName());
      txt_employee_phone.setText(employeeInfo.getPhoneNumber());
      txt_employee_address.setText(employeeInfo.getAddress());
      
      if (employeeInfo.getGender()) {
        rd_male.setSelected(true);
      } else {
        rd_female.setSelected(true);
      }
      
      if (employeeInfo.getBirthday() != null) {
        dp_employee_dob.setValue(employeeInfo.getBirthday().toLocalDate());
      }
    }
    
    SystemService systemService = new SystemService();
    this.systemSetting = systemService.getSystemInfo();
    if (this.systemSetting != null) {
      txt_shop_name.setText(systemSetting.getShopName());
      txt_shop_address.setText(systemSetting.getShopAddress());
      txt_shop_phone.setText(systemSetting.getShopPhone());
      txt_shop_tax.setText(systemSetting.getTaxCode());
    }
  }
  
  public void hideSystemContainer() {
    this.system_container.setVisible(false);
    this.system_container.setManaged(false);
  }
}
