package com.example.controllers.ComponentControllers;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.Card.EmployeeCardController;
import com.example.services.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EmployeeManagementController {
  @FXML
  private TextField txt_search_employee;
  @FXML
  private Button btn_search_employee;
  @FXML
  private Button btn_add_employee;
  @FXML
  private VBox employee_container;
  
  @FXML
  public void initialize() {
    this.setup();
  }
  
  private void setup() {
    try {
      EmployeeService employeeService = new EmployeeService();
      List<EmployeeInfo> list = employeeService.getAllEmployeeInfos();
      
      for (EmployeeInfo item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Employee.fxml"));
        Node employeeCard = loader.load();
        EmployeeCardController controller = loader.getController();
        controller.setEmployeeInfo(item);
        this.employee_container.getChildren().add(employeeCard);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/EmployeeForm.fxml"));
      Parent root = loader.load();
      EmployeeFormController controller = loader.getController();
      controller.setEmployeeInfo(null);
      
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm nhân viên mới");
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSearch() {
    String s = this.txt_search_employee.getText().trim().toLowerCase();
    this.employee_container.getChildren().clear();
    
    EmployeeService employeeService = new EmployeeService();
    List<EmployeeInfo> list = employeeService.getAllEmployeeInfos();
    
    for (var item : list) {
      try {
        if (String.valueOf(item.getEmployeeId()).trim().toLowerCase().contains(s) ||
                    item.getPhoneNumber().trim().toLowerCase().contains(s) ||
                    item.getFullName().trim().toLowerCase().contains(s)) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Employee.fxml"));
          Node employeeCard = loader.load();
          EmployeeCardController controller = loader.getController();
          controller.setEmployeeInfo(item);
          this.employee_container.getChildren().add(employeeCard);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
    
  }
}
