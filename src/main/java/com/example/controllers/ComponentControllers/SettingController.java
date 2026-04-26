package com.example.controllers.ComponentControllers;

import com.example.controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingController {
  @FXML
  private VBox system_container;
  
  private MainController mainController;
  
  public void setMainController(MainController main) {
    this.mainController = main;
  }
  
  @FXML
  public void initialize() {
    hideSystemContainer();
  }
  
  public void hideSystemContainer() {
    this.system_container.setVisible(false);
    this.system_container.setManaged(false);
  }
}
