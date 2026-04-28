module com.example {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires java.desktop;
  
  opens com.example.models to javafx.base;
  opens com.example.DTO to javafx.base;
  opens com.example to javafx.fxml;
  exports com.example;
  exports com.example.controllers;
  opens com.example.controllers to javafx.fxml;
  opens com.example.controllers.ComponentControllers to javafx.fxml;
  opens com.example.repositories to javafx.base;
  opens com.example.controllers.ComponentControllers.Card to javafx.fxml;
  opens com.example.controllers.ComponentControllers.SideBar to javafx.fxml;
}