module com.example {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  
  opens com.example.models to javafx.base;
  opens com.example.DTO to javafx.base;
  opens com.example to javafx.fxml;
  exports com.example;
  exports com.example.controllers;
  opens com.example.controllers to javafx.fxml;
  opens com.example.controllers.AdminControllers to javafx.fxml;
  opens com.example.controllers.EmployeeControllers to javafx.fxml;
  opens com.example.repositories to javafx.base;
}