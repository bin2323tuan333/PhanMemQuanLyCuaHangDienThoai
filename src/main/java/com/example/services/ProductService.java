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
  
  public List<ProductInfo> searchProduct(String key, int categoryId, int brandId, double minPrice, double maxPrice) {
    ProductRepository productRepo = new ProductRepository();
    return productRepo.searchProduct(key, categoryId, brandId, minPrice, maxPrice);
  }
  
  public ProductInfo getProductInfoById(int id) {
    ProductRepository productRepo = new ProductRepository();
    ProductInfo item = productRepo.getProductInfoById(id);
    return item;
  }
  
  public void addProduct(ProductInfo productInfo) {
    ProductRepository productRepo = new ProductRepository();
    productRepo.insertProduct(productInfo);
  }
  
  public void updateProduct(ProductInfo productInfo) {
    ProductRepository productRepo = new ProductRepository();
    productRepo.updateProduct(productInfo);
  }
  
  public void deleteProduct(int productId) {
    ProductRepository productRepo = new ProductRepository();
    productRepo.deleteProduct(productId);
  }
  
}
