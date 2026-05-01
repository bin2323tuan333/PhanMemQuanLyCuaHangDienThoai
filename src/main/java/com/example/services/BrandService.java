package com.example.services;

import com.example.models.Brand;
import com.example.repositories.BrandRepository;

import java.util.List;

public class BrandService {
  public Brand getBrandById(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    Brand brand = brandRepository.getBrandById(brandId);
    return brand;
  }
  
  public List<Brand> getAllBrands() {
    BrandRepository brandRepository = new BrandRepository();
    return brandRepository.getAllBrands();
  }
}
