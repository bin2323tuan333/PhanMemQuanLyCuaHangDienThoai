package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.EmployeeInfo;
import com.example.models.Account;
import com.example.models.Employee;
import com.example.services.AccountService;
import com.example.services.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;


public class EmployeeFormController {
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
  @FXML
  private TextField txt_address;
  @FXML
  private TextField txt_phone;
  @FXML
  private TextField txt_salary;
  @FXML
  private RadioButton rd_male;
  @FXML
  private RadioButton rd_female;
  @FXML
  private RadioButton rd_active;
  @FXML
  private RadioButton rd_inactive;
  @FXML
  private DatePicker dp_dob;
  private ToggleGroup genderGroup;
  private ToggleGroup activeGroup;
  
  
  private EmployeeInfo employeeInfo;
  
  
  public void setEmployeeInfo(EmployeeInfo employeeInfo) {
    this.employeeInfo = employeeInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
    genderGroup = new ToggleGroup();
    rd_male.setToggleGroup(genderGroup);
    rd_female.setToggleGroup(genderGroup);
    activeGroup = new ToggleGroup();
    rd_active.setToggleGroup(activeGroup);
    rd_inactive.setToggleGroup(activeGroup);
    rd_male.setSelected(true);
    rd_active.setSelected(true);
  }
  
  public void setup() {
    if (employeeInfo == null) {
      btn_delete.setVisible(false);
      btn_delete.setManaged(false);
      btn_update.setVisible(false);
      btn_update.setManaged(false);
    } else {
      btn_add.setVisible(false);
      btn_add.setManaged(false);
      show();
    }
  }
  
  public void show() {
    if (employeeInfo != null) {
      txt_id.setText(String.valueOf(employeeInfo.getEmployeeId()));
      txt_name.setText(employeeInfo.getFullName());
      txt_address.setText(employeeInfo.getAddress());
      txt_phone.setText(employeeInfo.getPhoneNumber());
      txt_salary.setText(String.valueOf(employeeInfo.getSalary()));
      
      if (employeeInfo.getGender()) {
        rd_male.setSelected(true);
      } else {
        rd_female.setSelected(true);
      }
      if (employeeInfo.getStatus()) {
        rd_active.setSelected(true);
      } else {
        rd_inactive.setSelected(true);
      }
      if (employeeInfo.getBirthday() != null) {
        dp_dob.setValue((employeeInfo.getBirthday()).toLocalDate());
      }
    }
  }
  
  private void clearForm() {
    if (txt_id != null) txt_id.clear();
    if (txt_name != null) txt_name.clear();
    if (txt_address != null) txt_address.clear();
    if (txt_phone != null) txt_phone.clear();
    if (txt_salary != null) txt_salary.clear();
    
    rd_male.setSelected(true);
    rd_active.setSelected(true);
    if (dp_dob != null) dp_dob.setValue(null);
  }
  
  public void handleBtnAdd() {
    EmployeeService employeeService = new EmployeeService();
    Employee newEmployee = getEmployeeDataFromForm();
    if (newEmployee != null) {
      employeeService.addEmployee(newEmployee);
      int employeeId = employeeService.getEmployeeIdByPhone(newEmployee.getPhoneNumber());
      Account acc = new Account();
      acc.setEmployeeId(employeeId);
      acc.setPassword("123456");
      acc.setUsername(newEmployee.getPhoneNumber().trim());
      acc.setRoleId(1);
      AccountService accountService = new AccountService();
      accountService.insertAccount(acc);
      closeForm();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please check the data and try again.", ButtonType.OK);
      alert.showAndWait();
    }
  }
  
  public void handleBtnUpdate() {
    EmployeeService employeeService = new EmployeeService();
    if (employeeInfo != null) {
      Employee updatedEmployee = getEmployeeDataFromForm();
      if (updatedEmployee != null) {
        updatedEmployee.setEmployeeId(employeeInfo.getEmployeeId());
        employeeService.updateEmployee(updatedEmployee);
        
        closeForm();
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please check the data and try again.", ButtonType.OK);
        alert.showAndWait();
      }
    }
  }
  
  public void handleBtnDelete() {
    EmployeeService employeeService = new EmployeeService();
    if (employeeInfo != null) {
      employeeService.deleteEmployee(employeeInfo.getEmployeeId());
      closeForm();
    }
  }
  
  public void handleBtnCancel() {
    closeForm();
  }
  
  private Employee getEmployeeDataFromForm() {
    try {
      Employee emp = new Employee();
      emp.setFullName(txt_name.getText().trim());
      emp.setAddress(txt_address.getText().trim());
      emp.setPhoneNumber(txt_phone.getText().trim());
      emp.setSalary(Double.parseDouble(txt_salary.getText().trim()));
      emp.setGender(rd_male.isSelected());
      emp.setStatus(rd_active.isSelected());
      emp.setBirthday(Date.valueOf(dp_dob.getValue()));
      return emp;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private void closeForm() {
    Stage stage = (Stage) btn_cancel.getScene().getWindow();
    stage.close();
  }
  
}
