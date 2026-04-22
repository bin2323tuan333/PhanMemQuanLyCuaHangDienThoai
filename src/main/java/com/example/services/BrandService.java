package com.example.services;

import com.example.models.Brand;
import com.example.repositories.BrandRepository;

public class BrandService {
  public Brand getBrandById(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    Brand brand = brandRepository.getBrandById(brandId);
    return brand;
  }
}
