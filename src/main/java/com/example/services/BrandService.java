package com.example.services;

import com.example.models.Brand;
import com.example.models.Category;
import com.example.repositories.BrandRepository;
import com.example.repositories.CategoryRepository;

import java.util.List;

public class BrandService {
  
  public int getOrInsertNewBrand(String brandName) {
    BrandRepository repo = new BrandRepository();
    Brand br = repo.findByName(brandName);
    if (br != null) {
      return br.getBrandId();
    }
    
    Brand newBr = new Brand();
    newBr.setBrandName(brandName);
    repo.insertBrand(newBr);
    return repo.findByName(brandName).getBrandId();
  }
  
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
  
  public void updateBrand(Brand b) {
    BrandRepository brandRepository = new BrandRepository();
    brandRepository.updateBrand(b);
  }
  
  public void deleteBrand(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    brandRepository.deleteBrand(brandId);
  }
  
  
  public boolean hasProduct(int brandId) {
    BrandRepository brandRepository = new BrandRepository();
    return brandRepository.hasProduct(brandId);
  }
}
