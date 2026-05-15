package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.*;
import com.example.controllers.ComponentControllers.Card.BillDetailController;
import com.example.models.Bill;
import com.example.models.Customer;
import com.example.repositories.BillRepository;
import com.example.services.BillService;
import com.example.utils.AppSection;
import com.example.utils.PdfInvoiceGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BillFormController {
  @FXML
  private Button btn_add;
  @FXML
  private Button btn_update;
  @FXML
  private Button btn_cancel;
  @FXML
  private Button btn_delete;
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name_customer;
  @FXML
  private TextField txt_total_price;
  @FXML
  private TextField txt_name_employee;
  @FXML
  private VBox bill_detail_container;
  
  private BillInfo billInfo;
  private BillRepository bill = new BillRepository();
  
  public void setBillInfo(BillInfo billInfo) {
    this.billInfo = billInfo;
    setup();
  }
  
  @FXML
  public void initialize() {
  
  }
  
  public void setup() {
    this.btn_update.setVisible(false);
    this.btn_update.setManaged(false);
    if (billInfo != null) {
      txt_id.setText("HD_" + String.valueOf(billInfo.getBillId()));
      txt_name_customer.setText(billInfo.getCustomer().getFullName());
      txt_total_price.setText(String.format("%,.0f", billInfo.getTotalAmount()));
      txt_name_employee.setText(billInfo.getEmployee().getFullName());
      
      loadBillDetails();
    }
    if (AppSection.Instance().isEmployee()) {
      this.btn_delete.setVisible(false);
      this.btn_delete.setManaged(false);
    }
  }
  
  public void handleBtnPrint() {
    BillService billService = new BillService();
    List<BillDetailInfo> details = billService.getBillDetailInfoByBillId(billInfo.getBillId());
    
    List<CartInfo> cartList = new ArrayList<>();
    for (BillDetailInfo detail : details) {
      ProductInfo productInfo = new ProductInfo();
      productInfo.setProductName(detail.getProduct().getProductName());
      productInfo.setPrice(detail.getUnitPrice());
      
      CartInfo cartInfo = new CartInfo();
      cartInfo.setProductInfo(productInfo);
      cartInfo.setQuantity(detail.getQuantity());
      
      cartList.add(cartInfo);
    }
    
    Customer c = billInfo.getCustomer();
    CustomerInfo customer = new CustomerInfo();
    customer.setCustomerName(c.getFullName());
    customer.setPhone(c.getPhoneNumber());
    
    String total = String.format("%,.0f đ", billInfo.getTotalAmount());
    
    Window window = txt_id.getScene().getWindow();
    PdfInvoiceGenerator generator = new PdfInvoiceGenerator();
    boolean success = generator.saveInvoiceWithChooser(window, customer, cartList, total);
    
    Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
    alert.setTitle(success ? "Thành công" : "Lỗi");
    alert.setHeaderText(null);
    alert.setContentText(success ? "Xuất hóa đơn PDF thành công!" : "Xuất hóa đơn thất bại!");
    alert.showAndWait();
  }
  
  public void handleBtnUpdate() {
    Bill updatedBill = getBillFromForm();
    if (updatedBill != null) {
      bill.updateBill(updatedBill);
      closeForm();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Lỗi");
      alert.setHeaderText(null);
      alert.setContentText("Dữ liệu không hợp lệ. Vui lòng kiểm tra lại.");
      alert.showAndWait();
    }
    
  }
  
  public void handleBtnDelete() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Xác nhận xóa");
    alert.setHeaderText(null);
    alert.setContentText("Bạn có chắc chắn muốn xóa hóa đơn này?");
    
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      bill.deleteBill(billInfo.getBillId());
      closeForm();
    }
  }
  
  public void handleBtnCancel() {
    closeForm();
  }
  
  private void closeForm() {
    Stage stage = (Stage) txt_id.getScene().getWindow();
    stage.close();
  }
  
  public void loadBillDetails() {
    BillService billService = new BillService();
    List<BillDetailInfo> list = billService.getBillDetailInfoByBillId(billInfo.getBillId());
    try {
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/BillDetail.fxml"));
        Node node = loader.load();
        BillDetailController controller = loader.getController();
        controller.setBillDetailInfo(item);
        this.bill_detail_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private Bill getBillFromForm() {
    Bill bill = new Bill();
    bill.setBillId(Integer.parseInt(txt_id.getText()));
    bill.setCustomerId(billInfo.getCustomer().getCustomerId());
    bill.setEmployeeId(billInfo.getEmployee().getEmployeeId());
    bill.setInvoiceDate(billInfo.getInvoiceDate());
    bill.setTotalAmount(Double.parseDouble(txt_total_price.getText().replaceAll(",", "")));
    return bill;
  }
}
