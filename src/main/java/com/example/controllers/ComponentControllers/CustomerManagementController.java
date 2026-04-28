package com.example.controllers.ComponentControllers;

import com.example.DTO.CustomerInfo;
import com.example.controllers.ComponentControllers.Card.CustomerCardController;
import com.example.services.CustomerService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

public class CustomerManagementController {
  @FXML
  private FlowPane customer_container;
  @FXML
  private TextField txt_search;
  
  
  @FXML
  public void initialize() {
    setup();
  }
  
  public void setup() {
    try {
      CustomerService customerService = new CustomerService();
      List<CustomerInfo> list = customerService.getAllCustomerInfos();
      for (CustomerInfo item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/Customer.fxml"));
        Node node = loader.load();
        CustomerCardController controller = loader.getController();
        controller.setCustomerInfo(item);
        this.customer_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void handleBtnAdd() {
  
  }
  
  public void handleBtnSearch() {
  
  }

//    @FXML private TextField txtSearch;
//    @FXML private TextField txtName;
//    @FXML private TextField txtPhone;
//    @FXML private TextField txtEmail;
//    @FXML private TextField txtAddress;
//
//    @FXML private TableView<CustomerItem> customerTable;
//    @FXML private TableColumn<CustomerItem, Number> colId;
//    @FXML private TableColumn<CustomerItem, String> colName;
//    @FXML private TableColumn<CustomerItem, String> colPhone;
//    @FXML private TableColumn<CustomerItem, String> colEmail;
//    @FXML private TableColumn<CustomerItem, String> colAddress;
//
//    private final ObservableList<CustomerItem> customerList = FXCollections.observableArrayList();
//
//    @FXML
//    public void initialize() {
//        colId.setCellValueFactory(data -> data.getValue().idProperty());
//        colName.setCellValueFactory(data -> data.getValue().nameProperty());
//        colPhone.setCellValueFactory(data -> data.getValue().phoneProperty());
//        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
//        colAddress.setCellValueFactory(data -> data.getValue().addressProperty());
//
//        loadMockData();
//
//        customerTable.getSelectionModel().selectedItemProperty().addListener(
//                (obs, oldValue, selected) -> {
//                    if (selected != null) {
//                        txtName.setText(selected.getName());
//                        txtPhone.setText(selected.getPhone());
//                        txtEmail.setText(selected.getEmail());
//                        txtAddress.setText(selected.getAddress());
//                    }
//                }
//        );
//    }
//
//    private void loadMockData() {
//        customerList.clear();
//        customerList.addAll(
//                new CustomerItem(1, "Nguyễn Văn A", "0987654321", "vana@gmail.com", "Đà Nẵng"),
//                new CustomerItem(2, "Trần Thị B", "0977111222", "thib@gmail.com", "Huế"),
//                new CustomerItem(3, "Lê Văn C", "0966333444", "vanc@gmail.com", "Quảng Nam")
//        );
//        customerTable.setItems(customerList);
//    }
//
//    @FXML
//    private void handleAdd() {
//        if (!validateInput()) return;
//
//        int newId = customerList.size() + 1;
//
//        customerList.add(new CustomerItem(
//                newId,
//                txtName.getText(),
//                txtPhone.getText(),
//                txtEmail.getText(),
//                txtAddress.getText()
//        ));
//
//        handleClear();
//    }
//
//    @FXML
//    private void handleUpdate() {
//        CustomerItem selected = customerTable.getSelectionModel().getSelectedItem();
//
//        if (selected == null) {
//            showAlert("Vui lòng chọn khách hàng cần sửa!");
//            return;
//        }
//
//        if (!validateInput()) return;
//
//        selected.setName(txtName.getText());
//        selected.setPhone(txtPhone.getText());
//        selected.setEmail(txtEmail.getText());
//        selected.setAddress(txtAddress.getText());
//
//        customerTable.refresh();
//        handleClear();
//    }
//
//    @FXML
//    private void handleDelete() {
//        CustomerItem selected = customerTable.getSelectionModel().getSelectedItem();
//
//        if (selected == null) {
//            showAlert("Vui lòng chọn khách hàng cần xóa!");
//            return;
//        }
//
//        customerList.remove(selected);
//        handleClear();
//    }
//
//    @FXML
//    private void handleSearch() {
//        String keyword = txtSearch.getText().trim().toLowerCase();
//
//        if (keyword.isEmpty()) {
//            customerTable.setItems(customerList);
//            return;
//        }
//
//        ObservableList<CustomerItem> filtered = FXCollections.observableArrayList();
//
//        for (CustomerItem c : customerList) {
//            if (c.getName().toLowerCase().contains(keyword)
//                    || c.getPhone().toLowerCase().contains(keyword)
//                    || c.getEmail().toLowerCase().contains(keyword)) {
//                filtered.add(c);
//            }
//        }
//
//        customerTable.setItems(filtered);
//    }
//
//    @FXML
//    private void handleRefresh() {
//        txtSearch.clear();
//        customerTable.setItems(customerList);
//    }
//
//    @FXML
//    private void handleClear() {
//        txtName.clear();
//        txtPhone.clear();
//        txtEmail.clear();
//        txtAddress.clear();
//        customerTable.getSelectionModel().clearSelection();
//    }
//
//    private boolean validateInput() {
//        if (txtName.getText().isEmpty()
//                || txtPhone.getText().isEmpty()
//                || txtEmail.getText().isEmpty()
//                || txtAddress.getText().isEmpty()) {
//            showAlert("Vui lòng nhập đầy đủ thông tin!");
//            return false;
//        }
//
//        if (!txtEmail.getText().contains("@")) {
//            showAlert("Email không hợp lệ!");
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
//    public static class CustomerItem {
//        private final IntegerProperty id;
//        private final StringProperty name;
//        private final StringProperty phone;
//        private final StringProperty email;
//        private final StringProperty address;
//
//        public CustomerItem(int id, String name, String phone, String email, String address) {
//            this.id = new SimpleIntegerProperty(id);
//            this.name = new SimpleStringProperty(name);
//            this.phone = new SimpleStringProperty(phone);
//            this.email = new SimpleStringProperty(email);
//            this.address = new SimpleStringProperty(address);
//        }
//
//        public int getId() { return id.get(); }
//        public String getName() { return name.get(); }
//        public String getPhone() { return phone.get(); }
//        public String getEmail() { return email.get(); }
//        public String getAddress() { return address.get(); }
//
//        public void setName(String name) { this.name.set(name); }
//        public void setPhone(String phone) { this.phone.set(phone); }
//        public void setEmail(String email) { this.email.set(email); }
//        public void setAddress(String address) { this.address.set(address); }
//
//        public IntegerProperty idProperty() { return id; }
//        public StringProperty nameProperty() { return name; }
//        public StringProperty phoneProperty() { return phone; }
//        public StringProperty emailProperty() { return email; }
//        public StringProperty addressProperty() { return address; }
//    }
}
