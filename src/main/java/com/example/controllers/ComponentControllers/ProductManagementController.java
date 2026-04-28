package com.example.controllers.AdminControllers;

import com.example.DTO.ProductInfo;
import com.example.controllers.ComponentControllers.Card.ProductCardController;
import com.example.services.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class ProductManagementController {
  @FXML
  private TextField txt_search;
  @FXML
  private Button btn_search_product;
  @FXML
  private Button btn_add_product;
  @FXML
  private ComboBox<String> cbb_brand;
  @FXML
  private ComboBox<String> cbb_category;
  @FXML
  private ComboBox<String> cbb_price;
  @FXML
  private FlowPane product_container;
  
  @FXML
  public void initialize() {
    setup();
  }
  
  private void setup() {
    try {
      ProductService productService = new ProductService();
      List<ProductInfo> list = productService.getAllProductInfos();
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Product.fxml"));
        Node node = loader.load();
        ProductCardController controller = loader.getController();
        controller.setProduct(item);
        this.product_container.getChildren().add(node);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  public void handleBtnAdd() {
  
  }
  
  public void handleBtnSearch() {
  
  }
  
  public void handleCbb() {
    
  }

//    @FXML private TextField txtSearch;
//    @FXML private TextField txtName;
//    @FXML private TextField txtBrand;
//    @FXML private TextField txtPrice;
//    @FXML private TextField txtQuantity;
//
//    @FXML private TableView<ProductItem> productTable;
//    @FXML private TableColumn<ProductItem, Number> colId;
//    @FXML private TableColumn<ProductItem, String> colName;
//    @FXML private TableColumn<ProductItem, String> colBrand;
//    @FXML private TableColumn<ProductItem, Number> colPrice;
//    @FXML private TableColumn<ProductItem, Number> colQuantity;
//
//    private final ObservableList<ProductItem> productList = FXCollections.observableArrayList();
//
//    @FXML
//    public void initialize() {
//        colId.setCellValueFactory(data -> data.getValue().idProperty());
//        colName.setCellValueFactory(data -> data.getValue().nameProperty());
//        colBrand.setCellValueFactory(data -> data.getValue().brandProperty());
//        colPrice.setCellValueFactory(data -> data.getValue().priceProperty());
//        colPrice.setCellFactory(tc -> new TableCell<ProductItem, Number>() {
//            @Override
//            protected void updateItem(Number item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    setText(String.format("%,.0f VND", item.doubleValue()));
//                }
//            }
//        });
//        colQuantity.setCellValueFactory(data -> data.getValue().quantityProperty());
//
//        loadMockData();
//
//        productTable.getSelectionModel().selectedItemProperty().addListener(
//                (obs, oldValue, selected) -> {
//                    if (selected != null) {
//                        txtName.setText(selected.getName());
//                        txtBrand.setText(selected.getBrand());
//                        txtPrice.setText(String.valueOf(selected.getPrice()));
//                        txtQuantity.setText(String.valueOf(selected.getQuantity()));
//                    }
//                }
//        );
//    }
//
//    private void loadMockData() {
//        productList.clear();
//        productList.addAll(
//                new ProductItem(1, "iPhone 15 Pro Max", "Apple", 31990000, 10),
//                new ProductItem(2, "Samsung Galaxy S24", "Samsung", 24990000, 8),
//                new ProductItem(3, "OPPO Reno 11", "OPPO", 10990000, 15),
//                new ProductItem(4, "Xiaomi 14", "Xiaomi", 17990000, 12)
//        );
//        productTable.setItems(productList);
//    }
//
//    @FXML
//    private void handleAdd() {
//        if (!validateInput()) return;
//
//        int newId = productList.size() + 1;
//        String name = txtName.getText();
//        String brand = txtBrand.getText();
//        double price = Double.parseDouble(txtPrice.getText());
//        int quantity = Integer.parseInt(txtQuantity.getText());
//
//        productList.add(new ProductItem(newId, name, brand, price, quantity));
//        handleClear();
//    }
//
//    @FXML
//    private void handleUpdate() {
//        ProductItem selected = productTable.getSelectionModel().getSelectedItem();
//
//        if (selected == null) {
//            showAlert("Vui lòng chọn sản phẩm cần sửa!");
//            return;
//        }
//
//        if (!validateInput()) return;
//
//        selected.setName(txtName.getText());
//        selected.setBrand(txtBrand.getText());
//        selected.setPrice(Double.parseDouble(txtPrice.getText()));
//        selected.setQuantity(Integer.parseInt(txtQuantity.getText()));
//
//        productTable.refresh();
//        handleClear();
//    }
//
//    @FXML
//    private void handleDelete() {
//        ProductItem selected = productTable.getSelectionModel().getSelectedItem();
//
//        if (selected == null) {
//            showAlert("Vui lòng chọn sản phẩm cần xóa!");
//            return;
//        }
//
//        productList.remove(selected);
//        handleClear();
//    }
//
//    @FXML
//    private void handleSearch() {
//        String keyword = txtSearch.getText().trim().toLowerCase();
//
//        if (keyword.isEmpty()) {
//            productTable.setItems(productList);
//            return;
//        }
//
//        ObservableList<ProductItem> filtered = FXCollections.observableArrayList();
//
//        for (ProductItem p : productList) {
//            if (p.getName().toLowerCase().contains(keyword)
//                    || p.getBrand().toLowerCase().contains(keyword)) {
//                filtered.add(p);
//            }
//        }
//
//        productTable.setItems(filtered);
//    }
//
//    @FXML
//    private void handleRefresh() {
//        txtSearch.clear();
//        productTable.setItems(productList);
//    }
//
//    @FXML
//    private void handleClear() {
//        txtName.clear();
//        txtBrand.clear();
//        txtPrice.clear();
//        txtQuantity.clear();
//        productTable.getSelectionModel().clearSelection();
//    }
//
//    private boolean validateInput() {
//        if (txtName.getText().isEmpty()
//                || txtBrand.getText().isEmpty()
//                || txtPrice.getText().isEmpty()
//                || txtQuantity.getText().isEmpty()) {
//            showAlert("Vui lòng nhập đầy đủ thông tin!");
//            return false;
//        }
//
//        try {
//            Double.parseDouble(txtPrice.getText());
//            Integer.parseInt(txtQuantity.getText());
//        } catch (NumberFormatException e) {
//            showAlert("Giá và số lượng phải là số!");
//            return false;
//        }
//
//        return true;
//    }
//
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public static class ProductItem {
//        private final IntegerProperty id;
//        private final StringProperty name;
//        private final StringProperty brand;
//        private final DoubleProperty price;
//        private final IntegerProperty quantity;
//
//        public ProductItem(int id, String name, String brand, double price, int quantity) {
//            this.id = new SimpleIntegerProperty(id);
//            this.name = new SimpleStringProperty(name);
//            this.brand = new SimpleStringProperty(brand);
//            this.price = new SimpleDoubleProperty(price);
//            this.quantity = new SimpleIntegerProperty(quantity);
//        }
//
//        public int getId() { return id.get(); }
//        public String getName() { return name.get(); }
//        public String getBrand() { return brand.get(); }
//        public double getPrice() { return price.get(); }
//        public int getQuantity() { return quantity.get(); }
//
//        public void setName(String name) { this.name.set(name); }
//        public void setBrand(String brand) { this.brand.set(brand); }
//        public void setPrice(double price) { this.price.set(price); }
//        public void setQuantity(int quantity) { this.quantity.set(quantity); }
//
//        public IntegerProperty idProperty() { return id; }
//        public StringProperty nameProperty() { return name; }
//        public StringProperty brandProperty() { return brand; }
//        public DoubleProperty priceProperty() { return price; }
//        public IntegerProperty quantityProperty() { return quantity; }
//    }
}