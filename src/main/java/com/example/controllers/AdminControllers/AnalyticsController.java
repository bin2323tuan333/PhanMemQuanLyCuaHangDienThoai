package com.example.controllers.AdminControllers;

import com.example.DTO.MarketShare;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
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
  private BarChart<String, Number> revenueProfitChart;
  @FXML
  private TableView<MarketShare> marketShareTable;
  
  @FXML
  private TableColumn<MarketShare, String> col_region;
  @FXML
  private TableColumn<MarketShare, String> col_marketShare;
  @FXML
  private TableColumn<MarketShare, String> col_growth;
  
  @FXML
  public void initialize() {
    totalRevenue.setText("1,284,500,000 VNĐ");
    totalOrders.setText("42,890");
    avgOrderValue.setText("29,950,000 VNĐ");
    customerGrowth.setText("+1,240");
    
    // Set dữ liệu cho biểu đồ
    setupChart();
    
    // Set dữ liệu cho bảng thị phần
    setupTable();
  }
  
  private void setupChart() {
    XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
    revenueSeries.setName("Doanh thu (Triệu VNĐ)");
    revenueSeries.getData().addAll(
            new XYChart.Data<>("Tháng 1", 120000),
            new XYChart.Data<>("Tháng 2", 135000),
            new XYChart.Data<>("Tháng 3", 150000),
            new XYChart.Data<>("Tháng 4", 180000),
            new XYChart.Data<>("Tháng 5", 210000),
            new XYChart.Data<>("Tháng 6", 250000)
    );
    
    XYChart.Series<String, Number> profitSeries = new XYChart.Series<>();
    profitSeries.setName("Lợi nhuận (Triệu VNĐ)");
    profitSeries.getData().addAll(
            new XYChart.Data<>("Tháng 1", 45000),
            new XYChart.Data<>("Tháng 2", 50000),
            new XYChart.Data<>("Tháng 3", 55000),
            new XYChart.Data<>("Tháng 4", 65000),
            new XYChart.Data<>("Tháng 5", 80000),
            new XYChart.Data<>("Tháng 6", 95000)
    );
    
    revenueProfitChart.getData().addAll(revenueSeries, profitSeries);
  }
  
  private void setupTable() {
    col_region.setCellValueFactory(new PropertyValueFactory<>("region"));
    col_marketShare.setCellValueFactory(new PropertyValueFactory<>("marketShare"));
    col_growth.setCellValueFactory(new PropertyValueFactory<>("growth"));
    
    ObservableList<MarketShare> data = FXCollections.observableArrayList(
            new MarketShare("Bắc Mỹ", "42%", "+8.5%"),
            new MarketShare("Châu Âu", "28%", "+5.2%"),
            new MarketShare("Châu Á - Thái Bình Dương", "18%", "+15.3%"),
            new MarketShare("Mỹ Latinh", "7%", "+12.1%"),
            new MarketShare("Trung Đông & Châu Phi", "5%", "+9.8%")
    );
    
    marketShareTable.setItems(data);
  }
}