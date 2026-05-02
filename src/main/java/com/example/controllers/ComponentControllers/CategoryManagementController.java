package com.example.controllers.ComponentControllers;

import com.example.controllers.ComponentControllers.Card.BrandCardController;
import com.example.controllers.ComponentControllers.Card.CategoryCardController;
import com.example.models.Brand;
import com.example.models.Category;
import com.example.services.BrandService;
import com.example.services.CategoryService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CategoryManagementController {
  @FXML
  private FlowPane brand_container;
  @FXML
  private FlowPane category_container;
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    this.category_container.getChildren().clear();
    this.brand_container.getChildren().clear();
    try {
      for (var item : categories) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Category.fxml"));
        Node node = loader.load();
        CategoryCardController controller = loader.getController();
        controller.setCategory(item);
        this.category_container.getChildren().add(node);
      }
      for (var item : brands) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Brand.fxml"));
        Node node = loader.load();
        BrandCardController controller = loader.getController();
        controller.setBrand(item);
        this.brand_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void handleBtnAddBrand() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/BrandForm.fxml"));
      Parent root = loader.load();
      BrandFormController controller = loader.getController();
      controller.setBrand(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAddCategory() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CategoryForm.fxml"));
      Parent root = loader.load();
      CategoryFormController controller = loader.getController();
      controller.setCategory(null);
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
