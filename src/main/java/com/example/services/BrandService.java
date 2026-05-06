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
  public void insertBrand(Brand b) {
    BrandRepository brandRepository = new BrandRepository();
    brandRepository.insertBrand(b);
  }
  public  void updateBrand(Brand b) {
    BrandRepository brandRepository = new BrandRepository();
    brandRepository.updateBrand(b);
  }
  public void deleteBrand(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    brandRepository.deleteBrand(brandId);}


public boolean hasProduct(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    return brandRepository.hasProduct(brandId);
  }}
