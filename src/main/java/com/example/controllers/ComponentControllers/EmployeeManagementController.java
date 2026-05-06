package com.example.controllers.ComponentControllers;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.Card.EmployeeCardController;
import com.example.controllers.ComponentControllers.Form.EmployeeFormController;
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
    EmployeeService employeeService = new EmployeeService();
    List<EmployeeInfo> list = employeeService.getAllEmployeeInfos();
    render(list);
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/EmployeeForm.fxml"));
      Parent root = loader.load();
      EmployeeFormController controller = loader.getController();
      controller.setEmployeeInfo(null);
      controller.setEmployeeManagementController(this);
      controller.setReload(this::reload);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm nhân viên mới");
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    EmployeeService employeeService = new EmployeeService();
    List<EmployeeInfo> list = employeeService.getAllEmployeeInfos();
    render(list);
  }
  
  public void handleBtnSearch() {
    String s = this.txt_search_employee.getText().trim().toLowerCase();
    EmployeeService employeeService = new EmployeeService();
    List<EmployeeInfo> list = employeeService.searchEmployees(s);
    render(list);
  }
  
  public void render(List<EmployeeInfo> list) {
    this.employee_container.getChildren().clear();
    try {
      for (EmployeeInfo item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Employee.fxml"));
        Node employeeCard = loader.load();
        EmployeeCardController controller = loader.getController();
        controller.setEmployeeInfo(item);
        controller.setReload(this::reload);
        this.employee_container.getChildren().add(employeeCard);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void reload() {
    this.txt_search_employee.setText("");
    setup();
  }
}
