package com.example.services;

import javafx.scene.chart.XYChart;

import java.util.LinkedHashMap;
import java.util.Map;

public class TempRenevueService implements IRenevueService {
    public TempRenevueService() {
    }
    @Override
    public XYChart.Series<String, Number> getYearRevenueData() {
        Map<String, Double> yearData = new LinkedHashMap<>();
        XYChart.Series<String, Number> yearSeries = new XYChart.Series<>();

        yearData.put("JAN", 150.0);
        yearData.put("FEB", 210.0);
        yearData.put("MAR", 185.0);
        yearData.put("APR", 320.0);
        yearData.put("MAY", 450.0);
        yearData.put("JUN", 520.0);
        yearData.put("JUL", 480.0);
        yearData.put("AUG", 610.0);
        yearData.put("SEP", 730.0);
        yearData.put("OCT", 890.0);
        yearData.put("NOV", 1050.0);
        yearData.put("DEC", 1200.0);

        yearData.forEach((month, value) -> {
            yearSeries.getData().add(new XYChart.Data<>(month, value));
        });

        return yearSeries;
    }
    @Override
    public XYChart.Series<String, Number> getMonthRevenueData() {
        Map<String, Double> monthData = new LinkedHashMap<>();
        XYChart.Series<String, Number> monthSeries = new XYChart.Series<>();

        monthData.put("Tuần 1", 200.0);
        monthData.put("Tuần 2", 450.0);
        monthData.put("Tuần 3", 300.0);
        monthData.put("Tuần 4", 600.0);

        monthData.forEach((week, value) -> {
            monthSeries.getData().add(new XYChart.Data<>(week, value));
        });
        return monthSeries;
    }
    @Override
    public XYChart.Series<String, Number> getWeekRevenueData() {
        Map<String, Double> weekData = new LinkedHashMap<>();
        XYChart.Series<String, Number> weekSeries = new XYChart.Series<>();
        weekData.put("Mon", 120.0);
        weekData.put("Tue", 150.0);
        weekData.put("Wed", 100.0);
        weekData.put("Thu", 180.0);
        weekData.put("Fri", 250.0);
        weekData.put("Sat", 400.0);
        weekData.put("Sun", 450.0);

        weekData.forEach((day, value) -> {
            weekSeries.getData().add(new XYChart.Data<>(day, value));
        });
        return weekSeries;
    }
}
