package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.CustomerInfo;
import com.example.services.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;

public class CustomerFormController {
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
  private RadioButton rd_male;
  @FXML
  private RadioButton rd_female;
  @FXML
  private DatePicker dp_dob;
  private ToggleGroup genderGroup;
  
  private CustomerInfo customerInfo;
  
  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
    genderGroup = new ToggleGroup();
    rd_male.setToggleGroup(genderGroup);
    rd_female.setToggleGroup(genderGroup);
    rd_male.setSelected(true);
  }
  
  public void setup() {
    if (customerInfo == null) {
      if (btn_delete != null) {
        btn_delete.setVisible(false);
        btn_delete.setManaged(false);
      }
      if (btn_update != null) {
        btn_update.setVisible(false);
        btn_update.setManaged(false);
      }
      clearForm();
    } else {
      if (btn_add != null) {
        btn_add.setVisible(false);
        btn_add.setManaged(false);
      }
      show();
    }
  }
  
  public void show() {
    if (customerInfo != null) {
      txt_id.setText(String.valueOf(customerInfo.getCustomerId()));
      txt_name.setText(customerInfo.getCustomerName());
      txt_address.setText(customerInfo.getAddress());
      txt_phone.setText(customerInfo.getPhone());
      
      if (customerInfo.getGender()) {
        rd_male.setSelected(true);
      } else {
        rd_female.setSelected(true);
      }
      
      dp_dob.setValue(customerInfo.getDob().toLocalDate());
    }
  }
  
  private void clearForm() {
    if (txt_id != null) txt_id.clear();
    if (txt_name != null) txt_name.clear();
    if (txt_address != null) txt_address.clear();
    if (txt_phone != null) txt_phone.clear();
    
    rd_male.setSelected(true);
    if (dp_dob != null) dp_dob.setValue(null);
  }
  
  @FXML
  public void handleBtnAdd() {
    CustomerService customerService = new CustomerService();
    CustomerInfo newCustomer = getCustomerDataFromForm();
    if (newCustomer != null) {
      customerService.addCustomer(newCustomer);
      closeForm();
    }
  }
  
  @FXML
  public void handleBtnUpdate() {
    CustomerService customerService = new CustomerService();
    if (customerInfo != null) {
      CustomerInfo updatedCustomer = getCustomerDataFromForm();
      if (updatedCustomer != null) {
        updatedCustomer.setCustomerId(customerInfo.getCustomerId());
        customerService.updateCustomer(updatedCustomer);
        closeForm();
      }
    }
  }
  
  @FXML
  public void handleBtnDelete() {
    if (customerInfo != null) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Xác nhận");
      alert.setHeaderText(null);
      alert.setContentText("Bạn có chắc chắn muốn xóa khách hàng này?");
      
      if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
        CustomerService customerService = new CustomerService();
        customerService.deleteCustomer(customerInfo.getCustomerId());
        closeForm();
      }
    }
  }
  
  @FXML
  public void handleBtnCancel() {
    closeForm();
  }
  
  private CustomerInfo getCustomerDataFromForm() {
    try {
      CustomerInfo customer = new CustomerInfo();
      customer.setCustomerName(txt_name.getText().trim());
      customer.setAddress(txt_address.getText().trim());
      customer.setPhone(txt_phone.getText().trim());
      customer.setGender(rd_male.isSelected());
      customer.setDob(Date.valueOf(dp_dob.getValue()));
      
      return customer;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private void closeForm() {
    if (btn_cancel != null) {
      Stage stage = (Stage) btn_cancel.getScene().getWindow();
      stage.close();
    }
  }
}
