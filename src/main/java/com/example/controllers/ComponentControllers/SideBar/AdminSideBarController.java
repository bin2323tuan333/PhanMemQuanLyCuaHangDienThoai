package com.example.controllers.ComponentControllers.SideBar;

import com.example.controllers.ComponentControllers.SettingController;
import com.example.controllers.MainController;
import com.example.controllers.TopBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSideBarController {
  @FXML
  private Button btn_dashboard;
  @FXML
  private Button btn_product;
  @FXML
  private Button btn_supplier;
  @FXML
  private Button btn_category;
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
  private Button btn_logout;
  @FXML
  private TopBarController topBarController;
  
  private Button currentActiveButton;
  
  
  @FXML
  public void initialize() {
    currentActiveButton = btn_dashboard;
    btn_dashboard.getStyleClass().add("active-menu");
  }
  
  public void loadDefaultPage() {
    handleBtnDashBoard();
  }
  
  private void setActiveButton(Button newButton) {
    if (currentActiveButton != null) {
      currentActiveButton.getStyleClass().remove("active-menu");
    }
    newButton.getStyleClass().add("active-menu");
    currentActiveButton = newButton;
  }
  
  
  public void handleBtnDashBoard() {
    setActiveButton(btn_dashboard);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/DashBoard.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSupplier() {
    setActiveButton(btn_supplier);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/SupplierManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnCategory() {
    setActiveButton(btn_category);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CategoryManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnProduct() {
    setActiveButton(btn_product);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAnalytics() {
    setActiveButton(btn_analytics);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Analytics.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnCustomer() {
    setActiveButton(btn_customer);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CustomerManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnBill() {
    setActiveButton(btn_bill);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/BillManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnEmployee() {
    setActiveButton(btn_employee);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/EmployeeManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnLogout() {
    setActiveButton(btn_logout);
    Stage currentStage = (Stage) btn_logout.getScene().getWindow();
    currentStage.close();
    
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Login.fxml"));
      Parent root = loader.load();
      
      Stage loginStage = new Stage();
      loginStage.setScene(new Scene(root));
      loginStage.setTitle("Đăng nhập");
      loginStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSetting() {
    setActiveButton(btn_setting);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Setting.fxml"));
      Node page = loader.load();
      SettingController controller = loader.getController();
      controller.setEmployeeInfo(MainController.Instance().getEmployeeInfo());
      MainController.Instance().getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
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
