package com.example.services;

import com.example.DTO.ProductSale;

import java.util.List;

public interface IStatisticsService {
    public List<ProductSale> getTopSellingLast30Days();
    public double getTotalRevenueToday();
    public int getNewOrdersCount();
    public int getRemainingProducts();
    public int getTotalCustomers();
}
