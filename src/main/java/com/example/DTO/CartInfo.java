package com.example.DTO;

import com.example.controllers.ComponentControllers.Card.CartCardController;

public class CartInfo {
  private ProductInfo productInfo;
  private int quantity;
  private CartCardController controller; // <--- ĐÂY CHÍNH LÀ NÓ NÀY!
  
  public CartInfo(ProductInfo productInfo, int quantity, CartCardController controller) {
    this.productInfo = productInfo;
    this.quantity = quantity;
    this.controller = controller;
  }
  
  public ProductInfo getProductInfo() {
    return productInfo;
  }
  
  public void setProductInfo(ProductInfo productInfo) {
    this.productInfo = productInfo;
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public CartCardController getController() {
    return controller;
  }
  
  public void setController(CartCardController controller) {
    this.controller = controller;
  }
}
