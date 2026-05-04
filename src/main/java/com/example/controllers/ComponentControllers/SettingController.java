package com.example.controllers.ComponentControllers;

import com.example.DTO.EmployeeInfo;
import com.example.DTO.SystemSetting;
import com.example.controllers.MainController;
import com.example.models.Account;
import com.example.models.Employee;
import com.example.services.AccountService;
import com.example.services.EmployeeService;
import com.example.services.SystemService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

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
  
  public void handldeBtnUpdateSystem() {
    SystemService systemService = new SystemService();
    if (this.systemSetting == null) {
      this.systemSetting = new SystemSetting();
    }
    systemSetting.setShopName(txt_shop_name.getText().trim());
    systemSetting.setShopAddress(txt_shop_address.getText().trim());
    systemSetting.setShopPhone(txt_shop_phone.getText().trim());
    systemSetting.setTaxCode(txt_shop_tax.getText().trim());
    systemService.updateSystemInfo(systemSetting);
  }
  
  public void handleBtnUpdatePerson() {
    Employee emp = new Employee();
    emp.setEmployeeId(employeeInfo.getEmployeeId());
    
    emp.setFullName(txt_employee_name.getText().trim());
    emp.setPhoneNumber(txt_employee_phone.getText().trim());
    emp.setAddress(txt_employee_address.getText().trim());
    emp.setGender(rd_male.isSelected());
    
    emp.setSalary(employeeInfo.getSalary());
    emp.setStatus(employeeInfo.getStatus());
    
    if (dp_employee_dob.getValue() != null) {
      emp.setBirthday(Date.valueOf(dp_employee_dob.getValue()));
    } else if (employeeInfo.getBirthday() != null) {
      emp.setBirthday(employeeInfo.getBirthday());
    }
    
    EmployeeService employeeService = new EmployeeService();
    employeeService.updateEmployee(emp);
  }
  
  public void handleBtnChangePass() {
    AccountService accountService = new AccountService();
    Account acc = accountService.getAccountByEmployeeId(this.employeeInfo.getEmployeeId());
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ChangePass.fxml"));
      Parent root = loader.load();
      ChangePassController controller = loader.getController();
      controller.setAccount(acc);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Đổi mật khẩu");
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
}
