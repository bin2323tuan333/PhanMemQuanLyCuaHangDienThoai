package com.example.controllers.ComponentControllers;

import com.example.models.Product;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class CreateBillController {
  @FXML
  private FlowPane product_container;
  
  @FXML
  public void initialize() {
    product_container.setPrefWrapLength(1200);
    try {
      setup();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void setup() throws IOException {
    ProductService productService = new ProductService();
    
    List<Product> list = productService.getAllProducts();
    for (Product item : list) {
      FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
      
      Node node = productComp.load();
      ProductCardController controller = productComp.getController();
      controller.setProductId(item.getProductId());
      
      this.product_container.getChildren().add(node);
    }
  }
}
