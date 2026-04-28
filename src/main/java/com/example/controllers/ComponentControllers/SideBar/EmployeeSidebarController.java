package com.example.controllers.ComponentControllers;

import com.example.controllers.MainController;
import com.example.controllers.TopBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.IOException;

public class EmployeeSidebarController {
  @FXML
  private Button btn_sale;
  @FXML
  private Button btn_product;
  @FXML
  private Button btn_customer;
  @FXML
  private Button btn_receipt;
  @FXML
  private Button btn_person;
  @FXML
  private Button btn_setting;
  @FXML
  private Button btn_logout;
  @FXML
  private TopBarController topBarController;
  
  private Button currentActiveButton;
  
  
  @FXML
  public void initialize() {
    currentActiveButton = btn_sale;
    btn_sale.getStyleClass().add("active-menu");
  }
  
  public void loadDefaultPage() {
    loadPage("/com/example/component/CreateBill.fxml");
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
      case "btn_sale":
        loadPage("/com/example/component/CreateBill.fxml");
        break;
      case "btn_product":
        loadPage("/com/example/component/ProductManagement.fxml");
        break;
      case "btn_customer":
        loadPage("/com/example/component/Analytics.fxml");
        break;
      case "btn_receipt":
        loadPage("/com/example/admin/EmployeeManagement.fxml");
        break;
      case "btn_person":
        loadPage("/com/example/component/BillManagement.fxml");
        break;
      case "btn_setting":
        loadPage("/com/example/component/Setting.fxml");
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
      System.err.println("Lỗi load trang: " + fxmlPath);
    }
  }
}
