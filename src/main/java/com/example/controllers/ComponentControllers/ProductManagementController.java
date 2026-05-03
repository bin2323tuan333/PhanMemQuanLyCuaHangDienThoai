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
  
  private ProductService productService;
  
  @FXML
  public void initialize() {
    productService = new ProductService();  // ✅ Cache service
    
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    
    categories.add(0, new Category(0, "Tất cả"));
    brands.add(0, new Brand(0, "Tất cả"));
    
    this.cbb_category.setItems(FXCollections.observableArrayList(categories));
    this.cbb_brand.setItems(FXCollections.observableArrayList(brands));
    this.cbb_price.setItems(FXCollections.observableArrayList(
            "Tất cả", "Dưới 1.000.000", "1.000.000 - 5.000.000", "Trên 5.000.000"
    ));
    this.cbb_price.setValue("Tất cả");
    
    cbb_category.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    cbb_brand.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    cbb_price.valueProperty().addListener((obs, oldVal, newVal) -> handleCbb());
    
    setup();
  }
  
  private void setup() {
    List<ProductInfo> list = productService.getAllProductInfos();
    product_container.getChildren().clear();
    render(list);
  }
  
  public void handleBtnAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductForm.fxml"));
      Parent root = loader.load();
      ProductFormController productFormController = loader.getController();
      productFormController.setProductInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới sản phẩm");
      stage.showAndWait();
      
      resetFilters();
      setup();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnSearch() {
    handleCbb();
  }
  
  public void handleCbb() {
    String keyword = txt_search.getText().trim();
    
    Category selectedCategory = cbb_category.getValue();
    int categoryId = (selectedCategory != null && selectedCategory.getCategoryId() > 0)
                             ? selectedCategory.getCategoryId()
                             : -1;
    
    Brand selectedBrand = cbb_brand.getValue();
    int brandId = (selectedBrand != null && selectedBrand.getBrandId() > 0)
                          ? selectedBrand.getBrandId()
                          : -1;
    
    String selectedPrice = cbb_price.getValue();
    double minPrice = -1;
    double maxPrice = -1;
    
    if (selectedPrice != null && !selectedPrice.equals("Tất cả")) {
      switch (selectedPrice) {
        case "Dưới 1.000.000":
          minPrice = 0;
          maxPrice = 1000000;
          break;
        case "1.000.000 - 5.000.000":
          minPrice = 1000000;
          maxPrice = 5000000;
          break;
        case "Trên 5.000.000":
          minPrice = 5000000;
          maxPrice = Double.MAX_VALUE;
          break;
      }
    }
    List<ProductInfo> filteredList = productService.searchProduct(keyword.isEmpty() ? null : keyword, categoryId, brandId, minPrice, maxPrice);
    product_container.getChildren().clear();
    render(filteredList);
  }
  
  private void resetFilters() {
    txt_search.clear();
    cbb_category.getSelectionModel().selectFirst();
    cbb_brand.getSelectionModel().selectFirst();
    cbb_price.setValue("Tất cả");
  }
  
  public void render(List<ProductInfo> list) {
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
}