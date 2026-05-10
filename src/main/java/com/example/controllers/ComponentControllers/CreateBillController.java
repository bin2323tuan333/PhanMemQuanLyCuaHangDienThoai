package com.example.controllers.ComponentControllers;

import com.example.DTO.CartInfo;
import com.example.DTO.CustomerInfo;
import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.Card.CartCardController;
import com.example.controllers.ComponentControllers.Card.CustomerCardController;
import com.example.controllers.ComponentControllers.Card.ProductCardController;
import com.example.controllers.ComponentControllers.Form.CustomerFormController;
import com.example.controllers.ComponentControllers.Form.ProductFormController;
import com.example.models.Bill;
import com.example.models.Brand;
import com.example.models.Category;
import com.example.repositories.BillDetailRepository;
import com.example.services.*;
import com.example.utils.AppSection;
import com.example.utils.PdfInvoiceGenerator;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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

public class CreateBillController {
  @FXML
  private FlowPane productlist;
  @FXML
  private VBox customerlist;
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
  
  @FXML
  private ComboBox<Brand> cbb_brand;
  @FXML
  private ComboBox<Category> cbb_category;
  @FXML
  private ComboBox<String> cbb_price;
  
  private List<CartInfo> listCart;
  private List<ProductInfo> listProduct;
  private List<CustomerInfo> listCustomer;
  private CustomerInfo customer;
  
  @FXML
  public void initialize() {
    listCart = new ArrayList<>();
    productlist.prefWrapLengthProperty().bind(product_container.widthProperty());
    product_container.prefWidthProperty().bind(main_container.widthProperty().multiply(0.4));
    setup();
  }
  
  
  private void setup() {
    CategoryService categoryService = new CategoryService();
    BrandService brandService = new BrandService();
    List<Category> categories = categoryService.getAllCategorys();
    List<Brand> brands = brandService.getAllBrands();
    categories.add(0, new Category(0, "Tất cả"));
    brands.add(0, new Brand(0, "Tất cả"));
    
    this.cbb_category.setItems(FXCollections.observableArrayList(categories));
    this.cbb_brand.setItems(FXCollections.observableArrayList(brands));
    this.cbb_price.setItems(FXCollections.observableArrayList(
            "Tất cả",
            "Dưới 2.000.000",
            "2.000.000 - 10.000.000",
            "10.000.000 - 30.000.000",
            "Trên 30.000.000"
    ));
    this.cbb_price.setValue("Tất cả");
    
    cbb_category.valueProperty().addListener((obs, oldVal, newVal) -> handleBtnSearchProduct());
    cbb_brand.valueProperty().addListener((obs, oldVal, newVal) -> handleBtnSearchProduct());
    cbb_price.valueProperty().addListener((obs, oldVal, newVal) -> handleBtnSearchProduct());
    
    loadCustomers();
    loadProducts();
  }
  
  private void loadProducts() {
    ProductService productService = new ProductService();
    listProduct = productService.getAllProductInfos();
    this.renderProduct(listProduct);
  }
  
  private void loadCustomers() {
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> customers = customerService.getAllCustomerInfos();
    this.renderCustomer(customers);
  }
  
  private void renderProduct(List<ProductInfo> list) {
    this.productlist.getChildren().clear();
    try {
      for (ProductInfo item : list) {
        FXMLLoader productComp = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
        Node node = productComp.load();
        ProductCardController controller = productComp.getController();
        controller.setSale(true);
        controller.setProduct(item);
        controller.setCreateBillController(this);
        this.productlist.getChildren().add(node);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    
  }
  
  private void renderCustomer(List<CustomerInfo> list) {
    customerlist.getChildren().clear();
    for (CustomerInfo item : list) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Customer.fxml"));
        Node node = loader.load();
        CustomerCardController controller = loader.getController();
        controller.setCustomerInfo(item);
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
    CustomerService customerService = new CustomerService();
    List<CustomerInfo> list = customerService.searchCustomerInfos(keyword);
    renderCustomer(list);
  }
  
  public void handleBtnSearchProduct() {
    String keyword = txt_search_product.getText().trim();
    Category selectedCategory = cbb_category.getValue();
    int categoryId = (selectedCategory != null && selectedCategory.getCategoryId() > 0) ? selectedCategory.getCategoryId() : -1;
    Brand selectedBrand = cbb_brand.getValue();
    int brandId = (selectedBrand != null && selectedBrand.getBrandId() > 0) ? selectedBrand.getBrandId() : -1;
    
    String selectedPrice = cbb_price.getValue();
    double minPrice = -1;
    double maxPrice = -1;
    
    if (selectedPrice != null && !selectedPrice.equals("Tất cả")) {
      switch (selectedPrice) {
        case "Dưới 2.000.000":
          minPrice = 0;
          maxPrice = 2000000;
          break;
        case "2.000.000 - 10.000.000":
          minPrice = 2000000;
          maxPrice = 10000000;
          break;
        case "10.000.000 - 30.000.000":
          minPrice = 10000000;
          maxPrice = 30000000;
          break;
        case "Trên 30.000.000":
          minPrice = 30000000;
          maxPrice = Double.MAX_VALUE;
          break;
      }
    }
    ProductService productService = new ProductService();
    List<ProductInfo> filteredList = productService.searchProduct(keyword.isEmpty() ? null : keyword, categoryId, brandId, minPrice, maxPrice);
    renderProduct(filteredList);
  }
  
  
  public void handleBtnDeleteCart() {
    this.cartlist.getChildren().clear();
    this.listCart = new ArrayList<>();
    caculate();
  }
  
  public void handleBtnAddCustomer() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/CustomerForm.fxml"));
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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/ProductForm.fxml"));
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
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Thiếu thông tin");
      alert.setHeaderText(null);
      alert.setContentText("Vui lòng chọn khách hàng và thêm sản phẩm vào giỏ hàng trước khi tạo hóa đơn.");
      alert.showAndWait();
      return;
    }
    BillService billService = new BillService();
    Bill bill = new Bill();
    bill.setCustomerId(customer.getCustomerId());
    bill.setEmployeeId(AppSection.Instance().getAccount().getEmployeeId());
    
    double total = listCart.stream()
                           .mapToDouble(item -> item.getProductInfo().getPrice() * item.getQuantity())
                           .sum();
    bill.setTotalAmount(total);
    int billId = billService.addBill(bill);
    
    if (billId <= 0) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Lỗi tạo hóa đơn");
      alert.setHeaderText(null);
      alert.setContentText("Đã xảy ra lỗi khi tạo hóa đơn. Vui lòng thử lại.");
      alert.showAndWait();
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
    
    
    PdfInvoiceGenerator pdfService = new PdfInvoiceGenerator();
    boolean isSaved = pdfService.saveInvoiceWithChooser(
            main_container.getScene().getWindow(),
            this.customer,
            this.listCart,
            lb_total_price.getText()
    );
    
    
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


