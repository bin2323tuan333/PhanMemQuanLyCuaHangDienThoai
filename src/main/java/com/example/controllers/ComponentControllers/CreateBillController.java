package com.example.controllers.ComponentControllers;

import com.example.DTO.ProductInfo;
import com.example.models.Product;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CreateBillController {
  @FXML
  private FlowPane productlist;
  @FXML
  private VBox cartlist;
  @FXML
  private HBox main_container;
  @FXML
  private VBox product_container;
  @FXML
  private VBox cart_container;
  
  
  @FXML
  public void initialize() {
    productlist.prefWrapLengthProperty().bind(product_container.widthProperty());
    product_container.prefWidthProperty().bind(main_container.widthProperty().multiply(0.4));
    try {
      setup();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void setup() throws IOException {
    ProductService productService = new ProductService();
    
    List<ProductInfo> list = productService.getAllProductInfos();
    for (ProductInfo item : list) {
      FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
      Node node = productComp.load();
      ProductCardController controller = productComp.getController();
      controller.setProduct(item);
      controller.setCreateBillController(this);
      this.productlist.getChildren().add(node);
    }
    
    for (int i = 1; i < 2; i++) addProductEngine(1);
    
  }
  
  public void addProductEngine(int id) {
    try {
      ProductService productService = new ProductService();
      ProductInfo item = productService.getProductInfoById(id);
      FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Cart.fxml"));
      Node node = productComp.load();
      CartCardController cartCardController = productComp.getController();
      cartCardController.setup(item);
      cartCardController.setParentController(this);
      this.cartlist.getChildren().add(node);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteCart(int productId, HBox container) {
    this.cartlist.getChildren().remove(container);
    System.out.println("Đã xóa ID: " + productId);
  }
}
