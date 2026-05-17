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
import javafx.scene.control.*;
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
  @FXML
  private Label lb_status;
  
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
    lb_status.setText("");
    if (billInfo != null) {
      txt_id.setText("HD_" + String.valueOf(billInfo.getBillId()));
      txt_name_customer.setText(billInfo.getCustomer().getFullName());
      txt_total_price.setText(String.format("%,.0f", billInfo.getTotalAmount()));
      txt_name_employee.setText(billInfo.getEmployee().getFullName());
      if (billInfo.getStatus()) {
        lb_status.setText("ĐÃ THANH TOÁN");
        lb_status.setStyle("-fx-text-fill: #155724; -fx-padding: 6 12; -fx-background-radius: 4; -fx-font-weight: bold; -fx-font-size: 13px;");
        btn_delete.setText("Hoàn hàng");
        btn_delete.setVisible(true);
        btn_delete.setManaged(true);
      } else {
        lb_status.setText("ĐÃ HOÀN HÀNG");
        lb_status.setStyle("-fx-text-fill: #721c24; -fx-padding: 6 12; -fx-background-radius: 4; -fx-font-weight: bold; -fx-font-size: 13px;");
        btn_delete.setVisible(false);
        btn_delete.setManaged(false);
      }
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
    BillService billService = new BillService();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Xác nhận hoàn hàng");
    alert.setHeaderText(null);
    alert.setContentText("Bạn có chắc chắn muốn hoàn trả toàn bộ hàng hóa cho hóa đơn này không?");
    
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      Bill refundBill = new Bill();
      refundBill.setBillId(billInfo.getBillId());
      refundBill.setCustomerId(billInfo.getCustomer().getCustomerId());
      refundBill.setEmployeeId(billInfo.getEmployee().getEmployeeId());
      refundBill.setInvoiceDate(billInfo.getInvoiceDate());
      refundBill.setTotalAmount(billInfo.getTotalAmount());
      refundBill.setStatus(false);
      billService.updateBill(refundBill);
      
      billService.returnBill(refundBill.getBillId());
      
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
    bill.setStatus(this.billInfo.getStatus());
    bill.setBillId(Integer.parseInt(txt_id.getText()));
    bill.setCustomerId(billInfo.getCustomer().getCustomerId());
    bill.setEmployeeId(billInfo.getEmployee().getEmployeeId());
    bill.setInvoiceDate(billInfo.getInvoiceDate());
    bill.setTotalAmount(Double.parseDouble(txt_total_price.getText().replaceAll(",", "")));
    return bill;
  }
}
