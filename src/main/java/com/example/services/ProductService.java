package com.example.services;

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
  
}
