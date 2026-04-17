package com.example.controllers.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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
    private TableView<?> marketShareTable;

}
