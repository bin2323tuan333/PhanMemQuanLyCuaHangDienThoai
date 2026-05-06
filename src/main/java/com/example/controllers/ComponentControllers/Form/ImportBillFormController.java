package com.example.controllers.ComponentControllers.Form;

import com.example.DTO.ImportBillDetailInfo;
import com.example.DTO.ImportBillInfo;
import com.example.controllers.ComponentControllers.Card.ImportBillDetailCardController;
import com.example.services.BillService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ImportBillFormController {
  @FXML
  private TextField txt_id;
  @FXML
  private TextField txt_name_supplier;
  @FXML
  private TextField txt_total_price;
  @FXML
  private TextField txt_name_employee;
  @FXML
  private Button btn_cancel;
  @FXML
  private VBox bill_detail_container;


  private ImportBillInfo importBillInfo;

  public void setImportBillInfo(ImportBillInfo importBillInfo) {
    this.importBillInfo = importBillInfo;
    setup();
  }

  @FXML
  public void initialize() {

  }


  public void setup() {
    if (this.importBillInfo != null) {
      txt_id.setText(this.importBillInfo.getImportId() + "");
      txt_name_employee.setText(this.importBillInfo.getEmployee().getFullName() + "");
      txt_name_supplier.setText(this.importBillInfo.getSupplier().getName() + "");
      txt_total_price.setText("" + String.format("%,.0f",this.importBillInfo.getTotalAmount()));
    }

    loadImportBillDetails();
  }

  public void handleBtnUpdate() throws SQLException {

    BillService importBillService = new BillService();

    importBillService.updateImportBill(getImportBillformfromInput());
    close();

  }

  public void handleBtnDelete() {
    BillService importBillService = new BillService();
    importBillService.deleteImportBill(importBillInfo.getImportId());
    close();
  }

  public void handleBtnCancel() {
    close();
  }

  public ImportBillInfo getImportBillformfromInput() {
    ImportBillInfo i = new ImportBillInfo();
    i.setImportId(Integer.parseInt(txt_id.getText()));
    i.setEmployee(importBillInfo.getEmployee());
    i.setSupplier(importBillInfo.getSupplier());
    String text=txt_total_price.getText().replace(".", "").trim();
    i.setTotalAmount(Double.parseDouble(text));
    return i;
  }

  public void close() {
    Stage stage = (Stage) btn_cancel.getScene().getWindow();
    stage.close();
  }

  public void loadImportBillDetails() {
    BillService billService = new BillService();
    List<ImportBillDetailInfo> list = billService.getImportBillDetailInfoByBillId(importBillInfo.getImportId());
    try {
      for (var item : list) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/component/card/ImportBillDetail.fxml"));
        Node node = loader.load();
        ImportBillDetailCardController controller = loader.getController();
        controller.setImportBillDetailInfo(item);
        this.bill_detail_container.getChildren().add(node);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
