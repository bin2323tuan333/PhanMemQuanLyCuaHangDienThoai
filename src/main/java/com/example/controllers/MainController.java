package com.example.controllers;

import com.example.controllers.AdminControllers.AdminSideBarController;
import com.example.controllers.EmployeeControllers.EmployeeSidebarController;
import com.example.models.Account;
import com.example.services.AccountService;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
  @FXML
  private ScrollPane sideBar;
  @FXML
  private ScrollPane mainScrollPane;
  @FXML
  private AdminSideBarController adminSideBarController;
  @FXML
  private EmployeeSidebarController employeeSidebarController;
  @FXML
  private TopBarController topBarController;
  @FXML
  private BorderPane mainBorderPane;
  
  private boolean isExpanded = false;
  private final double expandedWidth = 200;
  private final double collapsedWidth = 0;
  private int accountId;
  
  private static MainController _instance;
  
  @FXML
  public ScrollPane getMainScrollPane() {
    return mainScrollPane;
  }
  
  public static MainController Instance() {
    return _instance;
  }
  
  @FXML
  public void initialize() {
    _instance = this;
  }
  
  public void setup() {
    AccountService accountService = new AccountService();
    Account acc = accountService.getAccountByID(this.getAccountId());
    System.out.println(this.accountId + " " + acc.toString());
    if (acc.getRoleId() == 2) {
      try {
        FXMLLoader loaderSideBar = new FXMLLoader(getClass().getResource("/com/example/admin/AdminSideBar.fxml"));
        sideBar.setContent(loaderSideBar.load());
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/com/example/admin/AdminDashBoard.fxml"));
        mainScrollPane.setContent(loaderMain.load());
        adminSideBarController = loaderSideBar.getController();
        sideBar.setVisible(false);
        sideBar.setManaged(false);
        adminSideBarController.setTopBarController(topBarController);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        FXMLLoader loaderSideBar = new FXMLLoader(getClass().getResource("/com/example/employee/EmployeeSideBar.fxml"));
        sideBar.setContent(loaderSideBar.load());
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/com/example/employee/EmployeeSale.fxml"));
        mainScrollPane.setContent(loaderMain.load());
        employeeSidebarController = loaderSideBar.getController();
        sideBar.setVisible(false);
        sideBar.setManaged(false);
        employeeSidebarController.setTopBarController(topBarController);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    topBarController.setMainController(this);
    topBarController.setup();
  }
  
  @FXML
  public void toggleSidebar() {
    double targetWidth = isExpanded ? collapsedWidth : expandedWidth;
    KeyValue keyValue = new KeyValue(sideBar.prefWidthProperty(), targetWidth);
    KeyFrame keyFrame = new KeyFrame(Duration.millis(300), keyValue);
    Timeline timeline = new Timeline(keyFrame);
    timeline.setOnFinished(event -> {
      if (!isExpanded) {
        sideBar.setVisible(false);
        sideBar.setManaged(false);
      }
    });
    if (!isExpanded) {
      sideBar.setVisible(true);
      sideBar.setManaged(true);
    }
    timeline.play();
    isExpanded = !isExpanded;
  }
  
  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
  
  public int getAccountId() {
    return this.accountId;
  }
}
