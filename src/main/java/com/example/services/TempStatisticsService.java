package com.example.services;

import com.example.DTO.ProductSale;

import java.util.ArrayList;
import java.util.List;

public class TempStatisticsService implements IStatisticsService {
    public List<ProductSale> getTopSellingLast30Days() {
        List<ProductSale> list = new ArrayList<>();

        list.add(new ProductSale(1, "Nike Air Force 1", "Shoes", 154));
        list.add(new ProductSale(2, "iPhone 15 Pro Max", "Gadgets", 98));
        list.add(new ProductSale(3, "Logitech G Pro X", "Gaming", 85));
        list.add(new ProductSale(4, "Macbook Air M3", "Laptop", 62));
        list.add(new ProductSale(5, "Sony WH-1000XM5", "Audio", 45));

        return list;
    }
}
