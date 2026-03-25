package com.example.services;

import javafx.scene.chart.XYChart;

public interface IRenevueService {
    public XYChart.Series<String, Number> getYearRevenueData();
    public XYChart.Series<String, Number> getMonthRevenueData();
    public XYChart.Series<String, Number> getWeekRevenueData();
}
