package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.Form.EmployeeFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeCardController {
  @FXML
  private HBox employee_card;
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_id;
  @FXML
  private Label lb_address;
  @FXML
  private Label lb_role;
  @FXML
  private Label lb_dob;
  @FXML
  private Label lb_status;

  private EmployeeInfo employeeInfo;
  private Runnable reload;

  public void setReload(Runnable runnable) {
    this.reload = runnable;
  }

  public void setEmployeeInfo(EmployeeInfo employeeInfo) {
    this.employeeInfo = employeeInfo;
    setup();
  }

  @FXML
  public void initialize() {
  }

  private void setup() {
    this.lb_name.setText(employeeInfo.getFullName());
    this.lb_dob.setText(employeeInfo.getBirthday().toString());
    this.lb_id.setText((employeeInfo.getRole().getRoleName()) + employeeInfo.getEmployeeId());
    this.lb_address.setText(employeeInfo.getAddress());
    this.lb_role.setText(employeeInfo.getRole().getRoleName());
    this.lb_phone.setText(employeeInfo.getPhoneNumber());
    this.lb_status.setText((employeeInfo.getStatus() ? "Hoạt động" : "Khóa"));
  }

  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/EmployeeForm.fxml"));
      Parent root = loader.load();
      EmployeeFormController controller = loader.getController();
      controller.setEmployeeInfo(this.employeeInfo);
      controller.setReload(reload);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm nhân viên mới");
      stage.showAndWait();


    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
