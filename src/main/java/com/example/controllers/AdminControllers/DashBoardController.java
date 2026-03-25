package com.example.controllers.AdminControllers;


import com.example.DTO.ProductSale;
import com.example.DTO.RecentBill;
import com.example.services.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class DashBoardController {
    @FXML private BarChart<String, Number> revenueBarChart;
    @FXML private TableView<ProductSale> topProductsTable;
    @FXML private TableColumn<ProductSale, Integer> col_id;
    @FXML private TableColumn<ProductSale, String> col_name;
    @FXML private TableColumn<ProductSale, String> col_category;
    @FXML private TableColumn<ProductSale, Integer> col_sold;
    @FXML private TableView<RecentBill> invoiceTable;
    @FXML private TableColumn<RecentBill, Integer> col_inv_id;
    @FXML private TableColumn<RecentBill, String> col_inv_customer;
    @FXML private TableColumn<RecentBill, Date> col_inv_date;
    @FXML private TableColumn<RecentBill, Double> col_inv_total;
    @FXML private TableColumn<RecentBill, String> col_inv_status;
    @FXML private Label lb_revenue;
    @FXML private Label lb_product;
    @FXML private Label lb_customer;
    @FXML private Label lb_order;

    private IRenevueService revenueService;
    private IStatisticsService statisticsService;
    private IBillService billService;

    @FXML
    public void initialize() {
        revenueService = new TempRenevueService();
        statisticsService = new TempStatisticsService();
        billService = new TempBillService();

        XYChart.Series<String, Number> data = revenueService.getMonthRevenueData();
        revenueBarChart.getData().add(data);
        lb_revenue.setText(String.format("%,.0f VNĐ", statisticsService.getTotalRevenueToday()));
        lb_product.setText(statisticsService.getRemainingProducts() + "");
        lb_customer.setText(statisticsService.getTotalCustomers() + "");
        lb_order.setText(statisticsService.getNewOrdersCount() + "");
        setupTable();
        loadTableData();
    }

    private void setupTable() {
        col_id.setCellValueFactory(cellData ->
                                           new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        col_name.setCellValueFactory(cellData ->
                                             new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        col_category.setCellValueFactory(cellData ->
                                                 new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategory()));

        col_sold.setCellValueFactory(cellData ->
                                             new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getSold()).asObject());
        col_inv_id.setCellValueFactory(new PropertyValueFactory<>("billId"));
        col_inv_customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_inv_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_inv_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        col_inv_status.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadTableData() {
        List<ProductSale> list = statisticsService.getTopSellingLast30Days();
        System.out.println("Check data: " + list.size());
        topProductsTable.setItems(FXCollections.observableList(list));


        List<RecentBill> data = billService.getRecentBills(10);
        if (data != null) {
            invoiceTable.setItems(FXCollections.observableArrayList(data));
        }
    }

    public void handle_month_revenue() {
        XYChart.Series<String, Number> data = revenueService.getMonthRevenueData();
        revenueBarChart.getData().setAll(data);
    }
    public void handle_week_revenue() {
        XYChart.Series<String, Number> data = revenueService.getWeekRevenueData();
        revenueBarChart.getData().setAll(data);
    }
    public void handle_year_revenue() {
        XYChart.Series<String, Number> data = revenueService.getYearRevenueData();
        revenueBarChart.getData().setAll(data);
    }

}
