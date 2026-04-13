package com.example.controllers;

import com.example.controllers.AdminControllers.SideBarController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {
  @FXML
  private TopBarController topBarController;
  @FXML
  private ScrollPane sideBar;
  @FXML
  private ScrollPane mainScrollPane;
  @FXML
  private SideBarController sideBarController;
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
//        SideBarController.contentArea = mainScrollPane;
    try {
      FXMLLoader loaderSideBar = new FXMLLoader(getClass().getResource("/com/example/admin/AdminSideBar.fxml"));
      sideBar.setContent(loaderSideBar.load());
      FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/com/example/admin/DashBoard.fxml"));
      mainScrollPane.setContent(loaderMain.load());
      
      
      sideBarController = loaderSideBar.getController();
      sideBar.setVisible(false);
      sideBar.setManaged(false);
      if (sideBarController != null) {
        sideBarController.loadDefaultPage();
      }
      
      if (topBarController != null) {
        topBarController.setMainController(this);
      }
      
    } catch (IOException e) {
      System.err.println("🚨 LỖI: Không tìm thấy file SideBar.fxml !!!");
      e.printStackTrace();
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
  
  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }
  
  public int getAccountId() {
    return this.accountId;
  }
}
