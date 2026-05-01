package com.example.controllers.ComponentControllers;

import com.example.DTO.ProductInfo;
import com.example.models.Brand;
import com.example.models.Category;
import com.example.models.Supplier;
import com.example.services.BrandService;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import com.example.services.SupplierService;
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
  @FXML
  private ComboBox<Supplier> cbb_supplier;
  
  private ProductInfo productInfo;
  
  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
  }
  
  @FXML
  public void initialize() {
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    SupplierService supplierService = new SupplierService();
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    List<Supplier> suppliers = supplierService.getAllSuppliers();
    cbb_category.setItems(FXCollections.observableArrayList(categories));
    cbb_brand.setItems(FXCollections.observableArrayList(brands));
    cbb_supplier.setItems(FXCollections.observableArrayList(suppliers));
  }
  
  
  public void setup() {
    if (productInfo == null) {
      if (btn_delete != null) {
        btn_delete.setVisible(false);
        btn_delete.setManaged(false);
      }
      if (btn_update != null) {
        btn_update.setVisible(false);
        btn_update.setManaged(false);
      }
      clearForm();
    } else {
      if (btn_add != null) {
        btn_add.setVisible(false);
        btn_add.setManaged(false);
      }
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
      if (productInfo.getSupplier() != null) {
        cbb_supplier.setValue(productInfo.getSupplier());
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
    cbb_supplier.getSelectionModel().clearSelection();
  }
  
  @FXML
  public void handleBtnAdd() {
    ProductService productService = new ProductService();
    ProductInfo newProduct = getProductDataFromForm();
    if (newProduct != null) {
//      productService.addProduct(newProduct);
      closeForm();
    }
  }
  
  @FXML
  public void handleBtnUpdate() {
    ProductService productService = new ProductService();
    if (productInfo != null) {
      ProductInfo updatedProduct = getProductDataFromForm();
      if (updatedProduct != null) {
        updatedProduct.setProductId(productInfo.getProductId());
//        productService.updateProduct(updatedProduct);
        closeForm();
      }
    }
  }
  
  @FXML
  public void handleBtnDelete() {
    ProductService productService = new ProductService();
    if (productInfo != null) {
//      productService.deleteProduct(productInfo.getProductId());
      closeForm();
    }
  }
  
  @FXML
  public void handleBtnCancel() {
    closeForm();
  }
  
  private ProductInfo getProductDataFromForm() {
    try {
      String name = txt_name.getText().trim();
      double price = Double.parseDouble(txt_price.getText().trim());
      int stock = Integer.parseInt(txt_stock.getText().trim());
      String description = txt_description.getText().trim();
      
      Category category = cbb_category.getValue();
      Brand brand = cbb_brand.getValue();
      Supplier supplier = cbb_supplier.getValue();
      
      ProductInfo p = new ProductInfo();
      p.setProductName(name);
      p.setPrice(price);
      p.setStock(stock);
      p.setDescription(description);
      p.setCategory(category);
      p.setBrand(brand);
      p.setSupplier(supplier);
      
      return p;
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    stage.close();
  }
}
