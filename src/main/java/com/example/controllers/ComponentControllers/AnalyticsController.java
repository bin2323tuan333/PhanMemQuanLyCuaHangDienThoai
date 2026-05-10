package com.example.controllers.ComponentControllers;

import com.example.DTO.CustomerStats;
import com.example.DTO.EmployeeStats;
import com.example.models.Bill;
import com.example.services.BillService;
import com.example.services.CustomerService;
import com.example.services.EmployeeService;
import com.example.services.ProductService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AnalyticsController {
  
  @FXML
  private Label totalRevenue;
  @FXML
  private Label totalOrders;
  @FXML
  private Label avgOrderValue;
  @FXML
  private Label customerGrowth;
  
  @FXML
  private BarChart<String, Number> revenue_chart;
  @FXML
  private PieChart category_pie_chart;
  
  @FXML
  private TableView<EmployeeStats> employee_table;
  @FXML
  private TableColumn<EmployeeStats, String> col_empName;
  @FXML
  private TableColumn<EmployeeStats, Integer> col_empProducts;
  
  @FXML
  private TableView<CustomerStats> customer_table;
  @FXML
  private TableColumn<CustomerStats, String> col_cusName;
  @FXML
  private TableColumn<CustomerStats, String> col_cusPhone;
  @FXML
  private TableColumn<CustomerStats, Integer> col_cusOrders;
  @FXML
  private TableColumn<CustomerStats, Double> col_cusTotal;
  
  @FXML
  public void initialize() {
    setupTableColumns();
    loadSummaryCards();
    loadRevenueChart();
    loadCategoryPieChart();
    loadEmployeeTable();
    loadCustomerTable();
  }
  
  private void setupTableColumns() {
    col_empName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    col_empProducts.setCellValueFactory(new PropertyValueFactory<>("productCount"));
    
    col_cusName.setCellValueFactory(new PropertyValueFactory<>("name"));
    col_cusPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    col_cusOrders.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
    col_cusTotal.setCellValueFactory(new PropertyValueFactory<>("totalSpentDisplay"));
  }
  
  
  private void loadSummaryCards() {
    BillService billService = new BillService();
    CustomerService customerService = new CustomerService();
    double revenue = billService.getTotalRevenue();
    int orders = (int) billService.getTotalOrders();
    double avgValue = orders > 0 ? revenue / orders : 0;
    int totalCustomers = customerService.getTotalCustomer();
    
    totalRevenue.setText(String.format("%,.0f đ", revenue));
    totalOrders.setText(String.valueOf(orders));
    avgOrderValue.setText(String.format("%,.0f đ", avgValue));
    customerGrowth.setText(String.valueOf(totalCustomers));
  }
  
  private void loadRevenueChart() {
    BillService billService = new BillService();
    revenue_chart.getData().clear();
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Doanh thu");
    series.getData().addAll(billService.getMonthlyRevenue());
    revenue_chart.getData().add(series);
  }
  
  private void loadCategoryPieChart() {
    ProductService productService = new ProductService();
    category_pie_chart.setData(FXCollections.observableArrayList(
            productService.getProductCategoryRatio()
    ));
  }
  
  private void loadEmployeeTable() {
    EmployeeService employeeService = new EmployeeService();
    employee_table.setItems(FXCollections.observableArrayList(
            employeeService.getTopEmployees()
    ));
  }
  
  private void loadCustomerTable() {
    CustomerService customerService = new CustomerService();
    customer_table.setItems(FXCollections.observableArrayList(
            customerService.getTopSpendingCustomers(5)
    ));
  }
}