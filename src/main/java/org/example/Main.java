package org.example;

import com.example.models.*;
import com.example.repositories.*;
import com.example.services.BrandService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    BrandService b2 = new BrandService();
    BrandRepository b = new BrandRepository();
    List<Brand> list = b.getAllBrands();
    List<Brand> list2 = new ArrayList<>();
    for (var item : list) {
      list2.add(b2.getBrandById(item.getBrandId()));
    }
    for (var item : list2) {
      System.out.println(item.getBrandId());
    }
  }
}