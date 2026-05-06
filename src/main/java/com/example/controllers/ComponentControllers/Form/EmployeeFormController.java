package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.EmployeeManagementController;
import com.example.models.Account;
import com.example.models.Employee;
import com.example.models.Role;
import com.example.services.AccountService;
import com.example.services.EmployeeService;
import com.example.services.RoleService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;


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
  private ComboBox<Role> cbb_role;
  @FXML
  private DatePicker dp_dob;
  private ToggleGroup genderGroup;
  private ToggleGroup activeGroup;
  
  
  private EmployeeManagementController employeeManagementController;
  private EmployeeInfo employeeInfo;
  private Runnable reload;
  
  public void setReload(Runnable runnable) {
    this.reload = runnable;
  }
  
  public void setEmployeeManagementController(EmployeeManagementController employeeManagementController) {
    this.employeeManagementController = employeeManagementController;
  }
  
  
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
    RoleService roleService = new RoleService();
    List<Role> roles = roleService.getAllRoles();
    this.cbb_role.setItems(FXCollections.observableArrayList(roles));
    this.cbb_role.getSelectionModel().selectFirst();
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
      
      DecimalFormat df = new DecimalFormat("#,###");
      txt_salary.setText(df.format(employeeInfo.getSalary()));
      
      
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
      
      for (Role role : cbb_role.getItems()) {
        if (role.getRoleId() == employeeInfo.getRole().getRoleId()) {
          cbb_role.getSelectionModel().select(role);
          break;
        }
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
    try {
      EmployeeService employeeService = new EmployeeService();
      Employee newEmployee = getEmployeeDataFromForm();
      if (newEmployee == null) {
        throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ. Vui lòng kiểm tra lại các trường.");
      }
      employeeService.isValidData(newEmployee);
      employeeService.addEmployee(newEmployee);
      
      int employeeId = employeeService.getEmployeeIdByPhone(newEmployee.getPhoneNumber());
      Role selectedRole = cbb_role.getValue();
      Account acc = new Account();
      acc.setEmployeeId(employeeId);
      acc.setPassword("123456");
      acc.setUsername(selectedRole.getRoleName() + "_" + employeeId);
      if (selectedRole != null) {
        acc.setRoleId(selectedRole.getRoleId());
      } else {
        acc.setRoleId(1);
      }
      
      AccountService accountService = new AccountService();
      accountService.insertAccount(acc);
      closeForm();
    } catch (IllegalArgumentException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
      alert.setTitle("Lỗi nhập liệu");
      alert.setHeaderText(null);
      alert.showAndWait();
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi hệ thống: " + e.getMessage(), ButtonType.OK);
      alert.setTitle("Lỗi hệ thống");
      alert.setHeaderText(null);
      alert.showAndWait();
      e.printStackTrace();
    }
  }
  
  public void handleBtnUpdate() {
    try {
      EmployeeService employeeService = new EmployeeService();
      if (employeeInfo == null) {
        throw new IllegalArgumentException("Không tìm thấy thông tin nhân viên để cập nhật.");
      }
      Employee updatedEmployee = getEmployeeDataFromForm();
      if (updatedEmployee == null) {
        throw new IllegalArgumentException("Dữ liệu đầu vào không hợp lệ. Vui lòng kiểm tra lại các trường.");
      }
      updatedEmployee.setEmployeeId(employeeInfo.getEmployeeId());
      employeeService.isValidData(updatedEmployee);
      employeeService.updateEmployee(updatedEmployee);
      closeForm();
    } catch (IllegalArgumentException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
      alert.setTitle("Lỗi nhập liệu");
      alert.setHeaderText(null);
      alert.showAndWait();
      
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi hệ thống: Hãy nhập đúng dữ liệu!");
      alert.setTitle("Lỗi hệ thống");
      alert.setHeaderText(null);
      alert.showAndWait();
      e.printStackTrace();
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
      String salaryText = txt_salary.getText().trim()
                                  .replace(".", "")
                                  .replace(",", "");
      emp.setSalary(Double.parseDouble(salaryText));
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
    reload.run();
  }
  
}
