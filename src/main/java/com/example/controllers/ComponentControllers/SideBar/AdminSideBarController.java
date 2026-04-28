package com.example.controllers.ComponentControllers.SideBar;

import com.example.controllers.MainController;
import com.example.controllers.TopBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminSideBarController {
  @FXML
  private Button btn_dashboard;
  @FXML
  private Button btn_product;
  @FXML
  private Button btn_customer;
  @FXML
  private Button btn_bill;
  @FXML
  private Button btn_employee;
  @FXML
  private Button btn_analytics;
  @FXML
  private Button btn_setting;
  @FXML
  private TopBarController topBarController;
  
  private Button currentActiveButton;
  
  
  @FXML
  public void initialize() {
    currentActiveButton = btn_dashboard;
    btn_dashboard.getStyleClass().add("active-menu");
  }
  
  public void loadDefaultPage() {
    loadPage("/com/example/component/DashBoard.fxml");
  }
  
  @FXML
  public void handle_menu_click(ActionEvent event) {
    Button clickedButton = (Button) event.getSource();
    if (currentActiveButton != null) {
      currentActiveButton.getStyleClass().remove("active-menu");
    }
    clickedButton.getStyleClass().add("active-menu");
    currentActiveButton = clickedButton;
    
    String buttonId = clickedButton.getId();
    switch (buttonId) {
      case "btn_dashboard":
        loadPage("/com/example/component/DashBoard.fxml");
        break;
      case "btn_product":
        loadPage("/com/example/component/ProductManagement.fxml");
        break;
      case "btn_analytics":
        loadPage("/com/example/component/Analytics.fxml");
        break;
      case "btn_employee":
        loadPage("/com/example/component/EmployeeManagement.fxml");
        break;
      case "btn_bill":
        loadPage("/com/example/component/BillManagement.fxml");
        break;
      case "btn_setting":
        loadPage("/com/example/component/Setting.fxml");
        break;
      case "btn_customer":
        loadPage("/com/example/component/CustomerManagement.fxml");
        break;
      case "btn_logout":
        System.out.println("Logout");
        break;
    }
  }
  
  public void setTopBarController(TopBarController topBarController) {
    this.topBarController = topBarController;
  }
  
  private void loadPage(String fxmlPath) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
