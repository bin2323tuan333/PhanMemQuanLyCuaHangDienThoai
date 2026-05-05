package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.ProductInfo;
import com.example.models.Brand;
import com.example.models.Category;
import com.example.models.Product;
import com.example.services.BrandService;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class ProductFormController {
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
  @FXML
  private TextField txt_stock;
  @FXML
  private TextField txt_description;
  @FXML
  private TextField txt_price;
  @FXML
  private ComboBox<Category> cbb_category;
  @FXML
  private ComboBox<Brand> cbb_brand;
  
  private ProductInfo productInfo;
  
  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    cbb_category.setItems(FXCollections.observableArrayList(categories));
    cbb_brand.setItems(FXCollections.observableArrayList(brands));
  }
  
  
  public void setup() {
    if (productInfo == null) {
      btn_delete.setVisible(false);
      btn_delete.setManaged(false);
      btn_update.setVisible(false);
      btn_update.setManaged(false);
      clearForm();
    } else {
      btn_add.setVisible(false);
      btn_add.setManaged(false);
      show();
    }
  }
  
  public void show() {
    if (productInfo != null) {
      txt_id.setText(String.valueOf(productInfo.getProductId()));
      txt_name.setText(productInfo.getProductName());
      txt_price.setText(String.valueOf(productInfo.getPrice()));
      txt_stock.setText(String.valueOf(productInfo.getStock()));
      txt_description.setText(productInfo.getDescription());
      
      if (productInfo.getCategory() != null) {
        cbb_category.setValue(productInfo.getCategory());
      }
      if (productInfo.getBrand() != null) {
        cbb_brand.setValue(productInfo.getBrand());
      }
    }
  }
  
  private void clearForm() {
    if (txt_id != null) txt_id.clear();
    if (txt_name != null) txt_name.clear();
    if (txt_price != null) txt_price.clear();
    if (txt_stock != null) txt_stock.clear();
    if (txt_description != null) txt_description.clear();
    
    cbb_category.getSelectionModel().clearSelection();
    cbb_brand.getSelectionModel().clearSelection();
  }
  
  @FXML
  public void handleBtnAdd() {
    ProductService productService = new ProductService();
    Product newProduct = getProductDataFromForm();
    if (newProduct != null) {
      productService.addProduct(newProduct);
      closeForm();
    }
  }
  
  @FXML
  public void handleBtnUpdate() {
    ProductService productService = new ProductService();
    if (productInfo != null) {
      Product updatedProduct = getProductDataFromForm();
      if (updatedProduct != null) {
        updatedProduct.setProductId(productInfo.getProductId());
        productService.updateProduct(updatedProduct);
        closeForm();
      }
    }
  }
  
  @FXML
  public void handleBtnDelete() {
    ProductService productService = new ProductService();
    if (productInfo != null) {
      productService.deleteProduct(productInfo.getProductId());
      closeForm();
    }
  }
  
  @FXML
  public void handleBtnCancel() {
    closeForm();
  }
  
  private Product getProductDataFromForm() {
    try {
      String name = txt_name.getText().trim();
      String priceText = txt_price.getText().trim();
      String stockText = txt_stock.getText().trim();
      String description = txt_description.getText().trim();
      
      Category category = cbb_category.getValue();
      Brand brand = cbb_brand.getValue();
      
      if (category == null || brand == null) {
        System.out.println("Lỗi: Phải chọn đầy đủ Category, Brand!");
        return null;
      }
      
      if (name.isEmpty() || priceText.isEmpty() || stockText.isEmpty()) {
        System.out.println("Lỗi: Không được để trống thông tin text!");
        return null;
      }
      
      double price = Double.parseDouble(priceText);
      int stock = Integer.parseInt(stockText);
      
      Product p = new Product();
      p.setProductName(name);
      p.setPrice(price);
      p.setStock(stock);
      p.setDescription(description);
      p.setCategoryId(category.getCategoryId());
      p.setBrandId(brand.getBrandId());
      
      return p;
    } catch (NumberFormatException e) {
      System.out.println("Lỗi: Giá và Số lượng phải là số hợp lệ!");
      e.printStackTrace();
      return null;
    }
  }
  
  private void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    stage.close();
  }
}
