package com.example.controllers.AdminControllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeManagementController {

    @FXML private TextField txtSearch;
    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtSalary;
    @FXML private ComboBox<String> cbGender;

    @FXML private TableView<EmployeeItem> employeeTable;
    @FXML private TableColumn<EmployeeItem, Number> colId;
    @FXML private TableColumn<EmployeeItem, String> colName;
    @FXML private TableColumn<EmployeeItem, String> colPhone;
    @FXML private TableColumn<EmployeeItem, String> colGender;
    @FXML private TableColumn<EmployeeItem, Number> colSalary;

    private final ObservableList<EmployeeItem> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbGender.setItems(FXCollections.observableArrayList("Nam", "Nữ"));

        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colPhone.setCellValueFactory(data -> data.getValue().phoneProperty());
        colGender.setCellValueFactory(data -> data.getValue().genderProperty());
        colSalary.setCellValueFactory(data -> data.getValue().salaryProperty());

        colSalary.setCellFactory(tc -> new TableCell<EmployeeItem, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%,.0f VND", item.doubleValue()));
                }
            }
        });

        loadMockData();

        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, selected) -> {
                    if (selected != null) {
                        txtName.setText(selected.getName());
                        txtPhone.setText(selected.getPhone());
                        cbGender.setValue(selected.getGender());
                        txtSalary.setText(String.valueOf(selected.getSalary()));
                    }
                }
        );
    }

    private void loadMockData() {
        employeeList.clear();
        employeeList.addAll(
                new EmployeeItem(1, "Nguyễn Văn A", "0987654321", "Nam", 8000000),
                new EmployeeItem(2, "Trần Thị B", "0977111222", "Nữ", 7500000),
                new EmployeeItem(3, "Lê Văn C", "0966333444", "Nam", 9000000)
        );
        employeeTable.setItems(employeeList);
    }

    @FXML
    private void handleAdd() {
        if (!validateInput()) return;

        int newId = employeeList.size() + 1;
        employeeList.add(new EmployeeItem(
                newId,
                txtName.getText(),
                txtPhone.getText(),
                cbGender.getValue(),
                Double.parseDouble(txtSalary.getText())
        ));

        handleClear();
    }

    @FXML
    private void handleUpdate() {
        EmployeeItem selected = employeeTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Vui lòng chọn nhân viên cần sửa!");
            return;
        }

        if (!validateInput()) return;

        selected.setName(txtName.getText());
        selected.setPhone(txtPhone.getText());
        selected.setGender(cbGender.getValue());
        selected.setSalary(Double.parseDouble(txtSalary.getText()));

        employeeTable.refresh();
        handleClear();
    }

    @FXML
    private void handleDelete() {
        EmployeeItem selected = employeeTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Vui lòng chọn nhân viên cần xóa!");
            return;
        }

        employeeList.remove(selected);
        handleClear();
    }

    @FXML
    private void handleSearch() {
        String keyword = txtSearch.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            employeeTable.setItems(employeeList);
            return;
        }

        ObservableList<EmployeeItem> filtered = FXCollections.observableArrayList();

        for (EmployeeItem e : employeeList) {
            if (e.getName().toLowerCase().contains(keyword)
                    || e.getPhone().toLowerCase().contains(keyword)) {
                filtered.add(e);
            }
        }

        employeeTable.setItems(filtered);
    }

    @FXML
    private void handleRefresh() {
        txtSearch.clear();
        employeeTable.setItems(employeeList);
    }

    @FXML
    private void handleClear() {
        txtName.clear();
        txtPhone.clear();
        txtSalary.clear();
        cbGender.setValue(null);
        employeeTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        if (txtName.getText().isEmpty()
                || txtPhone.getText().isEmpty()
                || cbGender.getValue() == null
                || txtSalary.getText().isEmpty()) {
            showAlert("Vui lòng nhập đầy đủ thông tin!");
            return false;
        }

        try {
            Double.parseDouble(txtSalary.getText());
        } catch (NumberFormatException e) {
            showAlert("Lương phải là số!");
            return false;
        }

        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class EmployeeItem {
        private final IntegerProperty id;
        private final StringProperty name;
        private final StringProperty phone;
        private final StringProperty gender;
        private final DoubleProperty salary;

        public EmployeeItem(int id, String name, String phone, String gender, double salary) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.phone = new SimpleStringProperty(phone);
            this.gender = new SimpleStringProperty(gender);
            this.salary = new SimpleDoubleProperty(salary);
        }

        public int getId() { return id.get(); }
        public String getName() { return name.get(); }
        public String getPhone() { return phone.get(); }
        public String getGender() { return gender.get(); }
        public double getSalary() { return salary.get(); }

        public void setName(String name) { this.name.set(name); }
        public void setPhone(String phone) { this.phone.set(phone); }
        public void setGender(String gender) { this.gender.set(gender); }
        public void setSalary(double salary) { this.salary.set(salary); }

        public IntegerProperty idProperty() { return id; }
        public StringProperty nameProperty() { return name; }
        public StringProperty phoneProperty() { return phone; }
        public StringProperty genderProperty() { return gender; }
        public DoubleProperty salaryProperty() { return salary; }
    }
}