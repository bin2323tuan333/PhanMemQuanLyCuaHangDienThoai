package com.example.services;

import com.example.DTO.ProductInfo;
import com.example.models.Product;
import com.example.repositories.ProductRepository;

import java.util.List;

public class ProductService {
  public Product getProductById(int productId) {
    ProductRepository productRepo = new ProductRepository();
    Product product = productRepo.getProductById(productId);
    return product;
  }
  
  public List<Product> getAllProducts() {
    ProductRepository productRepo = new ProductRepository();
    List<Product> list = productRepo.getAllProducts();
    return list;
  }
  
  public List<ProductInfo> getAllProductInfos() {
    ProductRepository productRepo = new ProductRepository();
    List<ProductInfo> list = productRepo.getAllProductInfos();
    return list;
  }
  
  public ProductInfo getProductInfoById(int id) {
    ProductRepository productRepo = new ProductRepository();
    ProductInfo item = productRepo.getProductInfoById(id);
    return item;
  }
  
}
