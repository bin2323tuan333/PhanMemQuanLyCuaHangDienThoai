package com.example.controllers.ComponentControllers.Form;

import com.example.models.Category;
import com.example.services.CategoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoryFormController {
  @FXML
  private Button btn_add;
  @FXML
  private Button btn_update;
  @FXML
  private Button btn_cancel;
  @FXML
  private Button btn_delete;
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name;
  
  private Category category;
  
  public void setCategory(Category category) {
    this.category = category;
    setup();
  }
  
  @FXML
  public void initialize() {
  }
  
  public void setup() {
    if (this.category != null) {
      this.txt_id.setText(String.valueOf(this.category.getCategoryId()));
      this.txt_name.setText(this.category.getCategoryName());
    }
    
    if (this.category == null) {
      if (btn_delete != null) {
        btn_delete.setVisible(false);
        btn_delete.setManaged(false);
      }
      if (btn_update != null) {
        btn_update.setVisible(false);
        btn_update.setManaged(false);
      }
    } else {
      if (btn_add != null) {
        btn_add.setVisible(false);
        btn_add.setManaged(false);
      }
    }
  }
  
  public void handleBtnAdd() {
    CategoryService categoryService = new CategoryService();
    Category c = new Category();
    c.setCategoryName(txt_name.getText());
    categoryService.insertCategory(c);
    closeForm();
  }
  
  public void handleBtnUpdate() {
    if (this.category == null) return;
    CategoryService categoryService = new CategoryService();
    this.category.setCategoryName(txt_name.getText());
    categoryService.updateCategory(this.category);
    closeForm();
  }
  
  public void handleBtnDelete() {
    if (this.category == null) return;
    CategoryService categoryService = new CategoryService();
    categoryService.deleteCategory(this.category.getCategoryId());
    closeForm();
  }
  
  public void handleBtnCancel() {
    closeForm();
  }
  
  public void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    if (stage != null) {
      stage.close();
    }
  }
  
}
