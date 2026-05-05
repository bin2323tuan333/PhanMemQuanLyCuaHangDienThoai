package com.example.controllers;

import com.example.DTO.EmployeeInfo;
import com.example.controllers.ComponentControllers.SideBar.AdminSideBarController;
import com.example.controllers.ComponentControllers.SideBar.EmployeeSidebarController;
import com.example.models.Account;
import com.example.services.AccountService;
import com.example.utils.AppSection;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;

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
  
  @FXML
  public ScrollPane getMainScrollPane() {
    return mainScrollPane;
  }
  
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    topBarController.setMainController(this);
    topBarController.setup();
    renderSidebar();
  }
  
  public void renderSidebar() {
    if (AppSection.Instance().isAdmin()) {
      try {
        FXMLLoader loaderSideBar = new FXMLLoader(getClass().getResource("/com/example/component/SideBar/AdminSideBar.fxml"));
        Node sidebarNode = loaderSideBar.load();
        adminSideBarController = loaderSideBar.getController();
        adminSideBarController.setTopBarController(topBarController);
        adminSideBarController.setMainController(this);
        sideBar.setContent(sidebarNode);
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/com/example/component/DashBoard.fxml"));
        mainScrollPane.setContent(loaderMain.load());
        sideBar.setVisible(false);
        sideBar.setManaged(false);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        FXMLLoader loaderSideBar = new FXMLLoader(getClass().getResource("/com/example/component/SideBar/EmployeeSideBar.fxml"));
        Node sidebarNode = loaderSideBar.load();
        employeeSidebarController = loaderSideBar.getController();
        employeeSidebarController.setTopBarController(topBarController);
        employeeSidebarController.setMainController(this);
        sideBar.setContent(sidebarNode);
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/com/example/component/CreateBill.fxml"));
        mainScrollPane.setContent(loaderMain.load());
        sideBar.setVisible(false);
        sideBar.setManaged(false);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
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
  
}
