package com.example.controllers.ComponentControllers;

import com.example.DTO.CartInfo;
import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.Card.CartCardController;
import com.example.controllers.ComponentControllers.Card.ProductCardController;
import com.example.controllers.ComponentControllers.Form.SupplierFormController;
import com.example.models.ImportBill;
import com.example.models.Supplier;
import com.example.repositories.ImportBillDetailRepository;
import com.example.services.BillService;
import com.example.services.ProductService;
import com.example.services.SupplierService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CreateImportBillController {
  
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
  private Label lb_total_price;
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_mail;
  @FXML
  private Label lb_address;
  @FXML
  private TextField txt_search_supplier;
  @FXML
  private TextField txt_search_product;
  
  private List<CartInfo> listCart;
  List<ProductInfo> listProduct;
  private Supplier supplier;
  
  @FXML
  public void initialize() {
    listCart = new ArrayList<>();
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
    
    listProduct = productService.getAllProductInfos();
    for (ProductInfo item : listProduct) {
      FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
      Node node = productComp.load();
      ProductCardController controller = productComp.getController();
      controller.setSale(true);
      controller.setProduct(item);
      controller.setCreateImportBillController(this);
      this.productlist.getChildren().add(node);
    }
  }
  
  public void addProductEngine(int id) {
    try {
      CartInfo existingItem = null;
      for (CartInfo cartItem : listCart) {
        if (cartItem.getProductInfo().getProductId() == id) {
          existingItem = cartItem;
          break;
        }
      }
      if (existingItem != null) {
        existingItem.getController().handleIncrease();
      } else {
        ProductService productService = new ProductService();
        ProductInfo newItem = productService.getProductInfoById(id);
        FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Cart.fxml"));
        Node node = productComp.load();
        CartCardController cartCardController = productComp.getController();
        cartCardController.setup(newItem);
        cartCardController.setParentController(this);
        CartInfo newCartInfo = new CartInfo(newItem, 1, cartCardController);
        listCart.add(newCartInfo);
        cartCardController.setItem(newCartInfo);
        this.cartlist.getChildren().add(node);
      }
      caculate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteCart(int productId, HBox container) {
    this.cartlist.getChildren().remove(container);
    for (CartInfo cartItem : listCart) {
      if (cartItem.getProductInfo().getProductId() == productId) {
        this.listCart.remove(cartItem);
        break;
      }
    }
    this.caculate();
  }
  
  public void caculate() {
    double price = 0;
    for (CartInfo item : listCart) {
      price += item.getProductInfo().getPrice() * item.getQuantity();
    }
    DecimalFormat df = new DecimalFormat("#,###");
    this.lb_total_price.setText(df.format(price) + " VNĐ");
  }
  
  public void decreaseQuantity(int productId) {
    for (CartInfo cartItem : listCart) {
      if (cartItem.getProductInfo().getProductId() == productId) {
        cartItem.setQuantity(cartItem.getQuantity() - 1);
      }
    }
  }
  
  public void increaseQuantity(int productId) {
    for (CartInfo cartItem : listCart) {
      if (cartItem.getProductInfo().getProductId() == productId) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
      }
    }
  }
  
  public void handleBtnSearchCustomer() {
    SupplierService supplierService = new SupplierService();
    this.supplier = supplierService.getSupplierByPhone(txt_search_supplier.getText());
    if (this.supplier != null) {
      this.lb_name.setText(this.supplier.getName());
      this.lb_phone.setText(this.supplier.getPhone());
      this.lb_mail.setText(this.supplier.getEmail());
      this.lb_address.setText(this.supplier.getAddress());
    }
  }
  
  public void handleBtnSearchProduct() {
    String keyword = txt_search_product.getText().trim().toLowerCase();
    this.productlist.getChildren().clear();
    
    for (ProductInfo item : listProduct) {
      if (keyword.isEmpty() ||
                  item.getProductName().toLowerCase().contains(keyword) ||
                  String.valueOf(item.getProductId()).contains(keyword)) {
        try {
          FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
          Node node = productComp.load();
          ProductCardController controller = productComp.getController();
          controller.setProduct(item);
          controller.setCreateImportBillController(this);
          controller.setCreateBillController(null);
          this.productlist.getChildren().add(node);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    
  }
  
  public void handleBtnCheckout() {
    if (supplier == null || listCart.isEmpty()) {
      return;
    }
    
    BillService importBillService = new BillService();
    ImportBill importBill = new ImportBill();
    importBill.setSupplierId(supplier.getSupplierId());
    importBill.setEmployeeId(1);
    
    double total = listCart.stream()
                           .mapToDouble(item -> item.getProductInfo().getPrice() * item.getQuantity())
                           .sum();
    importBill.setTotalAmount(total);
    
    int importBillId = importBillService.addImportBill(importBill);
    
    if (importBillId <= 0) {
      System.out.println("Tạo hóa đơn nhập không thành công, kiểm tra lại DB!");
      return;
    }
    
    ImportBillDetailRepository importBillDetailService = new ImportBillDetailRepository();
    for (CartInfo item : listCart) {
      importBillDetailService.insertImportBillDetail(
              importBillId,
              item.getProductInfo().getProductId(),
              item.getQuantity(),
              item.getProductInfo().getPrice()
      );
    }
    
    ProductService productService = new ProductService();
    for (CartInfo item : listCart) {
      productService.increaseStock(
              item.getProductInfo().getProductId(),
              item.getQuantity()
      );
    }
    
    this.listCart.clear();
    this.cartlist.getChildren().clear();
  }
  
  public void handleBtnDeleteCart() {
    this.cartlist.getChildren().clear();
    this.listCart = new ArrayList<>();
    caculate();
  }
  
  public void handleBtnAddCustomer() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/SupplierForm.fxml"));
      Parent root = loader.load();
      SupplierFormController controller = loader.getController();
      controller.setSupplier(null);
      Stage stage = new Stage();
      stage.setTitle("Thêm Nhà Cung Cấp Mới");
      stage.setScene(new Scene(root));
      stage.showAndWait();
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
  
  
  

