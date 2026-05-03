package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.CartInfo;
import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.CreateBillController;
import com.example.controllers.ComponentControllers.CreateImportBillController;
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
  private CreateBillController createBillController;
  private CreateImportBillController createImportBillController;
  
  public void setParentController(CreateBillController parentController) {
    this.createBillController = parentController;
  }
  
  public void setParentController(CreateImportBillController parentController) {
    this.createImportBillController = parentController;
  }
  
  public void setItem(CartInfo item) {
    this.item = item;
  }
  
  
  public void setup(ProductInfo p) {
    this.productId = p.getProductId();
    this.price = p.getPrice();
    this.lb_brand.setText("" + p.getBrand().getBrandName().charAt(0) + p.getBrand().getBrandName().charAt(1));
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_quantity.setText("1");
    this.lb_price.setText(df.format(p.getPrice()) + " VNĐ");
    this.lb_name.setText(p.getProductName());
    this.lb_category.setText(p.getCategory().getCategoryName());
  }
  
  public void handleDecrease() {
    int quantity = Integer.parseInt(this.lb_quantity.getText());
    if (quantity > 1) {
      this.lb_quantity.setText(--quantity + "");
      DecimalFormat df = new DecimalFormat("#,###");
      this.lb_price.setText(df.format(this.price * quantity) + " VNĐ");
      if (createBillController != null) {
        this.createBillController.decreaseQuantity(this.productId);
        this.createBillController.caculate();
      } else {
        this.createImportBillController.decreaseQuantity(this.productId);
        this.createImportBillController.caculate();
      }
      
    } else {
      if (this.createBillController != null) {
        this.createBillController.deleteCart(this.productId, this.cartitem_container);
      } else {
        this.createImportBillController.deleteCart(this.productId, this.cartitem_container);
      }
    }
  }
  
  public void handleIncrease() {
    int quantity = Integer.parseInt(this.lb_quantity.getText());
    int stock = this.item.getProductInfo().getStock();
    if (quantity >= stock && this.createImportBillController == null) {
      return;
    }
    this.lb_quantity.setText(++quantity + "");
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_price.setText(df.format(this.price * quantity) + " VNĐ");
    if (createBillController != null) {
      this.createBillController.increaseQuantity(this.productId);
      this.createBillController.caculate();
    } else {
      this.createImportBillController.increaseQuantity(this.productId);
      this.createImportBillController.caculate();
    }
    
  }
  
  public void handleDelete() {
    if (createBillController != null) {
      this.createBillController.deleteCart(this.productId, this.cartitem_container);
      this.createBillController.caculate();
    } else {
      this.createImportBillController.deleteCart(this.productId, this.cartitem_container);
      this.createImportBillController.caculate();
    }
    
  }
}
