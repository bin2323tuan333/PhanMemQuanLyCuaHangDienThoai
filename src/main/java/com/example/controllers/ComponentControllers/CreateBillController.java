package com.example.controllers.ComponentControllers;

import com.example.DTO.CartInfo;
import com.example.DTO.CustomerInfo;
import com.example.DTO.ImportBillInfo;
import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.Card.CartCardController;
import com.example.controllers.ComponentControllers.Card.CustomerCardController;
import com.example.controllers.ComponentControllers.Card.ProductCardController;
import com.example.models.Bill;
import com.example.models.Customer;
import com.example.models.ImportBill;
import com.example.repositories.BillDetailRepository;
import com.example.services.BillService;
import com.example.services.CustomerService;
import com.example.services.ProductService;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CreateBillController {
  @FXML
  private FlowPane productlist;
  @FXML
  private FlowPane customerlist;
  @FXML
  private VBox cartlist;
  @FXML
  private VBox customerBox;
  
  @FXML
  private HBox main_container;
  @FXML
  private VBox product_container;
  
  @FXML
  private Label lb_total_price;
  @FXML
  private Label lb_name;
  @FXML
  private Label lb_phone;
  @FXML
  private Label lb_dob;
  @FXML
  private Label lb_gender;
  @FXML
  private Label lb_address;
  @FXML
  private TextField txt_search_customer;
  @FXML
  private TextField txt_search_product;
  
  private List<CartInfo> listCart;
  private List<ProductInfo> listProduct;
  private List<CustomerInfo> listCustomer;
  private CustomerInfo customer;
  
  @FXML
  public void initialize() {
    listCart = new ArrayList<>();
    loadCustomers();
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
      controller.setCreateBillController(this);
      this.productlist.getChildren().add(node);
    }
  }
  
  private void loadCustomers() {
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> customers = customerService.getAllCustomerInfos();
    
    customerlist.getChildren().clear();
    
    for (CustomerInfo item : customers) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Customer.fxml"));
        Node node = loader.load();
        
        CustomerCardController controller = loader.getController();
        controller.setCustomerInfo(item);
        controller.setParentController(this);
        controller.setParentController(this);
        
        customerlist.getChildren().add(node);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
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
    String keyword = txt_search_customer.getText().trim().toLowerCase();
    
    this.customerlist.getChildren().clear();
    
    
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
          controller.setSale(true);
          controller.setCreateBillController(this);
          controller.setCreateImportBillController(null);
          this.productlist.getChildren().add(node);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    
  }
  
  
  public void handleBtnDeleteCart() {
    this.cartlist.getChildren().clear();
    this.listCart = new ArrayList<>();
    caculate();
  }
  
  public void handleBtnAddCustomer() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/CustomerForm.fxml"));
      Parent root = loader.load();
      CustomerFormController customerFormController = loader.getController();
      customerFormController.setCustomerInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAddProduct() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/ProductForm.fxml"));
      Parent root = loader.load();
      ProductFormController productFormController = loader.getController();
      productFormController.setProductInfo(null);
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Thêm mới");
      stage.showAndWait();
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnCancel() {
    Stage stage = (Stage) main_container.getScene().getWindow();
    stage.close();
  }
  
  public void handleBtnAddBill() {
    if (customer == null || listCart.isEmpty()) {
      return;
    }
    
    BillService billService = new BillService();
    Bill bill = new Bill();
    bill.setCustomerId(customer.getCustomerId());
    bill.setEmployeeId(1);
    
    double total = listCart.stream()
                           .mapToDouble(item -> item.getProductInfo().getPrice() * item.getQuantity())
                           .sum();
    bill.setTotalAmount(total);
    int billId = billService.addBill(bill);
    
    if (billId <= 0) {
      System.out.println("Tạo bill không thành công, kiểm tra lại DB!");
      return;
    }
    
    BillDetailRepository billDetailService = new BillDetailRepository();
    for (CartInfo item : listCart) {
      billDetailService.insertBillDetail(
              billId,
              item.getProductInfo().getProductId(),
              item.getQuantity(),
              item.getProductInfo().getPrice()
      );
    }
    
    ProductService productService = new ProductService();
    for (CartInfo item : listCart) {
      productService.decreaseStock(
              item.getProductInfo().getProductId(),
              item.getQuantity()
      );
    }
    
    this.listCart.clear();
    this.cartlist.getChildren().clear();
    
    
  }
  
  public void selectCustomer(CustomerInfo customer) {
    this.listCart.clear();
    this.cartlist.getChildren().clear();
    this.customer = customer;
    lb_name.setText(customer.getCustomerName());
    lb_phone.setText(customer.getPhone());
    lb_dob.setText(customer.getDob().toString());
    lb_gender.setText(customer.getGender() ? "Nữ" : "Nam");
    lb_address.setText(customer.getAddress());
  }
  
}


