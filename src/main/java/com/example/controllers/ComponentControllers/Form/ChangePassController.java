package com.example.controllers.ComponentControllers.Form;

import com.example.models.Account;
import com.example.services.AccountService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePassController {
  @FXML
  private TextField txt_oldPass;
  @FXML
  private TextField txt_newPass;
  @FXML
  private TextField txt_reNewPass;
  
  private Account account;
  
  public void setAccount(Account account) {
    this.account = account;
  }
  
  @FXML
  public void initialize() {
  
  }
  
  
  public void handleBtnSave() {
    String oldPass = txt_oldPass.getText().trim();
    String newPass = txt_newPass.getText().trim();
    String reNewPass = txt_reNewPass.getText().trim();
    
    if (this.account == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi: Không tìm thấy thông tin tài khoản!", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    
    if (oldPass.isEmpty() || newPass.isEmpty() || reNewPass.isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Vui lòng nhập đầy đủ tất cả các trường!", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    
    if (!oldPass.equals(this.account.getPassword())) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Mật khẩu cũ không chính xác!", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    
    if (!newPass.equals(reNewPass)) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Mật khẩu mới không khớp, vui lòng kiểm tra lại!", ButtonType.OK);
      alert.showAndWait();
      return;
    }
    
    AccountService accountService = new AccountService();
    accountService.changePassword(this.account.getUsername(), newPass);
    handleBtnCancel();
  }
  
  public void handleBtnCancel() {
    Stage stage = (Stage) txt_oldPass.getScene().getWindow();
    stage.close();
  }
}
