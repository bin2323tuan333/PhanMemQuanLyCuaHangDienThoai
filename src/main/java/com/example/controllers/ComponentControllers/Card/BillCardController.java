package com.example.controllers.ComponentControllers.Card;

import com.example.DTO.BillInfo;
import com.example.controllers.ComponentControllers.Form.BillFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BillCardController {
  @FXML
  private Label lb_id;
  @FXML
  private Label lb_customer_name;
  @FXML
  private Label lb_employee_name;
  @FXML
  private Label lb_customer_phone;
  @FXML
  private Label lb_employee_phone;
  @FXML
  private Label lb_customer_address;
  @FXML
  private Label lb_total;
  
  private BillInfo billInfo;
  
  public void setData(BillInfo billInfo) {
    this.billInfo = billInfo;
    lb_id.setText("HD_" + billInfo.getBillId());
    lb_customer_name.setText("" + billInfo.getCustomer().getFullName());
    lb_employee_name.setText("" + billInfo.getEmployee().getFullName());
    
    lb_customer_phone.setText("" + billInfo.getCustomer().getPhoneNumber());
    lb_employee_phone.setText("" + billInfo.getEmployee().getPhoneNumber());
    lb_customer_address.setText("" + billInfo.getCustomer().getAddress());
    
    lb_total.setText("" + String.format("%,.0f VNĐ", billInfo.getTotalAmount()));
  }
  
  @FXML
  public void handleClick() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/Form/BillForm.fxml"));
      Parent root = loader.load();
      BillFormController controller = loader.getController();
      if (controller != null && billInfo != null) {
        controller.setBillInfo(billInfo);
      }
      Stage stage = new Stage();
      stage.setTitle("Chi tiết / Cập nhật hóa đơn");
      stage.setScene(new Scene(root));
      stage.showAndWait();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

