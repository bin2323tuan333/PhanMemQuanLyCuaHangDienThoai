package com.example.controllers.ComponentControllers;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.Card.EmployeeCardController;
import com.example.services.EmployeeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
}
