package com.example.controllers.ComponentControllers;

import com.example.DTO.CartInfo;
import com.example.DTO.ProductInfo;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.text.DecimalFormat;

public class CartCardController {
  @FXML
  private HBox cartitem_container;
  @FXML
  private Label lb_brand;
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_category;
  @FXML
  private Label lb_quantity;
  @FXML
  private Label lb_price;
  @FXML
  private Button btn_decrese;
  @FXML
  private Button btn_increase;
  @FXML
  private Button btn_delete;
  
  private int productId;
  private CartInfo item;
  private double price;
  private CreateBillController parentController;
  
  public void setParentController(CreateBillController parentController) {
    this.parentController = parentController;
  }
  
  public void setItem(CartInfo item) {
    this.item = item;
  }
  
  
  public void setup(ProductInfo p) {
    this.productId = p.getProductId();
    this.price = p.getPrice();
    this.lb_brand.setText("" + p.getBrandName().charAt(0) + p.getBrandName().charAt(1));
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_quantity.setText("1");
    this.lb_price.setText(df.format(p.getPrice()) + " VNĐ");
    this.lb_name.setText(p.getProductName());
    this.lb_category.setText(p.getCategoryName());
  }
  
  public void handleDecrease() {
    int quantity = Integer.parseInt(this.lb_quantity.getText());
    if (quantity > 1) {
      this.lb_quantity.setText(--quantity + "");
      DecimalFormat df = new DecimalFormat("#,###");
      this.lb_price.setText(df.format(this.price * quantity) + " VNĐ");
      this.parentController.decreaseQuantity(this.productId);
      this.parentController.caculate();
    } else {
      if (this.parentController != null) {
        this.parentController.deleteCart(this.productId, this.cartitem_container);
      }
    }
  }
  
  public void handleIncrease() {
    int quantity = Integer.parseInt(this.lb_quantity.getText());
    int stock = this.item.getProductInfo().getStock();
    if (quantity >= stock) {
      return;
    }
    this.lb_quantity.setText(++quantity + "");
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_price.setText(df.format(this.price * quantity) + " VNĐ");
    this.parentController.increaseQuantity(this.productId);
    this.parentController.caculate();
  }
  
  public void handleDelete() {
    this.parentController.deleteCart(this.productId, this.cartitem_container);
    this.parentController.caculate();
  }
}
