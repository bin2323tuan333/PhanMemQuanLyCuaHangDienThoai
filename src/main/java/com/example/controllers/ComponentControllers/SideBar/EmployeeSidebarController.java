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
  private Button btn_setting;
  @FXML
  private Button btn_logout;
  @FXML
  private TopBarController topBarController;
  
  private Button currentActiveButton;
  
  private MainController mainController;
  
  public void setMainController(MainController mainController) {
    this.mainController = mainController;
  }
  
  @FXML
  public void initialize() {
    currentActiveButton = btn_sale;
    btn_sale.getStyleClass().add("active-menu");
  }
  
  public void loadDefaultPage() {
    handleBtnSale();
  }
  
  private void setActiveButton(Button newButton) {
    if (currentActiveButton != null) {
      currentActiveButton.getStyleClass().remove("active-menu");
    }
    newButton.getStyleClass().add("active-menu");
    currentActiveButton = newButton;
  }
  
  public void handleBtnSale() {
    setActiveButton(btn_sale);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      mainController.getMainScrollPane().setContent(page);
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
      mainController.getMainScrollPane().setContent(page);
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
      mainController.getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnReceipt() {
    setActiveButton(btn_receipt);
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/BillManagement.fxml"));
      Node page = loader.load();
      var controller = loader.getController();
      mainController.getMainScrollPane().setContent(page);
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
      controller.hideSystemContainer();
      mainController.getMainScrollPane().setContent(page);
      if (topBarController != null)
        topBarController.setTitle(currentActiveButton.getText());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void setTopBarController(TopBarController topBarController) {
    this.topBarController = topBarController;
  }
}
