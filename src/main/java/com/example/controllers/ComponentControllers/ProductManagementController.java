package com.example.controllers.ComponentControllers;

import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.Card.ProductCardController;
import com.example.models.Brand;
import com.example.models.Category;
import com.example.services.BrandService;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class ProductManagementController {
  @FXML
  private TextField txt_search;
  @FXML
  private Button btn_search_product;
  @FXML
  private Button btn_add_product;
  @FXML
  private ComboBox<Brand> cbb_brand;
  @FXML
  private ComboBox<Category> cbb_category;
  @FXML
  private ComboBox<String> cbb_price;
  @FXML
  private FlowPane product_container;
  
  @FXML
  public void initialize() {
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    this.cbb_category.setItems(FXCollections.observableArrayList(categories));
    this.cbb_brand.setItems(FXCollections.observableArrayList(brands));
    this.cbb_price.setItems(FXCollections.observableArrayList(
            "Tất cả", "Dưới 1.000.000", "1.000.000 - 5.000.000", "Trên 5.000.000"
    ));
    this.cbb_price.setValue("Tất cả");
    
    cbb_category.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    cbb_brand.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    cbb_price.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    txt_search.textProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    
    
    setup();
  }
  
  private void setup() {
    ProductService productService = new ProductService();
    List<ProductInfo> list = productService.getAllProductInfos();
    try {
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
        Node node = loader.load();
        ProductCardController controller = loader.getController();
        controller.setProduct(item);
        this.product_container.getChildren().add(node);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductForm.fxml"));
      Parent root = loader.load();
      ProductFormController productFormController = loader.getController();
      productFormController.setProductInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
      product_container.getChildren().clear();
      setup();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSearch() {
    handleCbb();
  }
  
  public void handleCbb() {
    String searchText = txt_search.getText().trim().toLowerCase();
    Category selectedCategory = cbb_category.getValue();
    Brand selectedBrand = cbb_brand.getValue();
    String selectedPrice = cbb_price.getValue();
    
    product_container.getChildren().clear();
    
    try {
      ProductService productService = new ProductService();
      List<ProductInfo> list = productService.getAllProductInfos();
      
      for (var item : list) {
        boolean matchesSearch = searchText.isEmpty() ||
                                        item.getProductName().toLowerCase().contains(searchText) ||
                                        String.valueOf(item.getProductId()).contains(searchText);
        
        boolean matchesCategory = selectedCategory == null ||
                                          selectedCategory.toString().equals("Tất cả") ||
                                          (item.getCategory() != null && item.getCategory().getCategoryName() != null && item.getCategory().getCategoryName().equals(selectedCategory.getCategoryName()));
        
        boolean matchesBrand = selectedBrand == null ||
                                       (item.getBrand() != null && item.getBrand().getBrandName() != null && item.getBrand().getBrandName().equals(selectedBrand.getBrandName()));
        
        boolean matchesPrice = true;
        
        if (selectedPrice != null && !selectedPrice.equals("Tất cả")) {
          matchesPrice = filterByPrice(item.getPrice(), selectedPrice);
        }
        
        if (matchesSearch && matchesCategory && matchesBrand && matchesPrice) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
          Node node = loader.load();
          ProductCardController controller = loader.getController();
          controller.setProduct(item);
          this.product_container.getChildren().add(node);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  private boolean filterByPrice(double price, String priceRange) {
    switch (priceRange) {
      case "Dưới 1.000.000":
        return price < 1000000;
      case "1.000.000 - 5.000.000":
        return price >= 1000000 && price <= 5000000;
      case "Trên 5.000.000":
        return price > 5000000;
      default:
        return true;
    }
  }
}