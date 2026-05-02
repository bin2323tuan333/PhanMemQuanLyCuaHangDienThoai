package com.example.controllers.ComponentControllers.Card;

import com.example.controllers.ComponentControllers.CategoryFormController;
import com.example.models.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryCardController {
  @FXML
  private Label lb_name;
  
  private Category category;
  
  public void setCategory(Category category) {
    this.category = category;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.lb_name.setText(this.category.getCategoryName());
  }
  
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CategoryForm.fxml"));
      Parent root = loader.load();
      CategoryFormController controller = loader.getController();
      controller.setCategory(this.category);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
