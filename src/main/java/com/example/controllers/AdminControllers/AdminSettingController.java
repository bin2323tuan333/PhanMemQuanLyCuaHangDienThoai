package com.example.controllers.AdminControllers;

import com.example.models.Employee;
import com.example.services.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AdminSettingController {

    @FXML
    private ToggleButton generalTab;
    @FXML
    private ToggleButton securityTab;
    @FXML
    private ToggleButton usersPermTab;

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea bioArea;
    @FXML
    private TextField companyField;
    @FXML
    private TextField industryField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button btn_save;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_changePassword;

    @FXML
    private Label lb_message;

    private ToggleButton currentActiveTab;
    private int employeeId;

    @FXML
    public void initialize() {
        setupTabs();
        loadUserData();
    }

    private void setupTabs() {
        currentActiveTab = generalTab;
        generalTab.setSelected(true);
        showGeneralTab();

        generalTab.setOnAction(event -> {
            if (generalTab.isSelected()) {
                currentActiveTab = generalTab;
                showGeneralTab();
            }
        });

        securityTab.setOnAction(event -> {
            if (securityTab.isSelected()) {
                currentActiveTab = securityTab;
                showSecurityTab();
            }
        });




        usersPermTab.setOnAction(event -> {
            if (usersPermTab.isSelected()) {
                currentActiveTab = usersPermTab;
                showUsersPermTab();
            }
        });
    }

    private void showGeneralTab() {
        clearAllContent();
        fullNameField.setVisible(true);
        emailField.setVisible(true);
        phoneField.setVisible(true);
        addressField.setVisible(true);
        bioArea.setVisible(true);
        btn_save.setVisible(true);
        btn_cancel.setVisible(true);
    }

    private void showSecurityTab() {
        clearAllContent();
        currentPasswordField.setVisible(true);
        newPasswordField.setVisible(true);
        confirmPasswordField.setVisible(true);
        btn_changePassword.setVisible(true);
    }

    private void showNotificationsTab() {
        clearAllContent();
        // Thêm các control cho tab thông báo
        Label notiLabel = new Label("Cài đặt thông báo đang được phát triển");
        notiLabel.setLayoutX(50);
        notiLabel.setLayoutY(50);
        // Cần thêm vào layout, tạm thời để trống
    }

    private void showUsersPermTab() {
        clearAllContent();
        // Thêm các control cho tab phân quyền
        Label permLabel = new Label("Quản lý phân quyền đang được phát triển");
        permLabel.setLayoutX(50);
        permLabel.setLayoutY(50);
    }

    private void clearAllContent() {
        fullNameField.setVisible(false);
        emailField.setVisible(false);
        phoneField.setVisible(false);
        addressField.setVisible(false);
        bioArea.setVisible(false);
        currentPasswordField.setVisible(false);
        newPasswordField.setVisible(false);
        confirmPasswordField.setVisible(false);
        btn_save.setVisible(false);
        btn_cancel.setVisible(false);
        btn_changePassword.setVisible(false);
    }

    private void loadUserData() {

        employeeId = 1;

        EmployeeService employeeService = new EmployeeService();
        Employee emp = employeeService.getEmployeeByID(employeeId);

        if (emp != null) {
            fullNameField.setText(emp.getFullName());
            addressField.setText(emp.getAddress());
            phoneField.setText(emp.getPhoneNumber());
            // emailField.setText(emp.getEmail()); // Nếu có email
        }
    }

    @FXML
    public void handleSave() {
        // Lưu thông tin
        lb_message.setText("Đã lưu thông tin thành công!");
        lb_message.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleCancel() {
        loadUserData(); // Reload lại dữ liệu cũ
        lb_message.setText("Đã hủy thay đổi");
        lb_message.setStyle("-fx-text-fill: orange;");
    }

    @FXML
    public void handleChangePassword() {
        String currentPwd = currentPasswordField.getText();
        String newPwd = newPasswordField.getText();
        String confirmPwd = confirmPasswordField.getText();

        if (currentPwd.isEmpty() || newPwd.isEmpty() || confirmPwd.isEmpty()) {
            lb_message.setText("Vui lòng nhập đầy đủ thông tin!");
            lb_message.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!newPwd.equals(confirmPwd)) {
            lb_message.setText("Mật khẩu mới không khớp!");
            lb_message.setStyle("-fx-text-fill: red;");
            return;
        }

        if (newPwd.length() < 6) {
            lb_message.setText("Mật khẩu phải có ít nhất 6 ký tự!");
            lb_message.setStyle("-fx-text-fill: red;");
            return;
        }

        // TODO: Gọi service để đổi mật khẩu
        lb_message.setText("Đổi mật khẩu thành công!");
        lb_message.setStyle("-fx-text-fill: green;");

        // Clear fields
        currentPasswordField.clear();
        newPasswordField.clear();
        confirmPasswordField.clear();
    }
}