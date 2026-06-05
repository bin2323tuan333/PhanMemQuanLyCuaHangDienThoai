package com.example.services;

import com.example.models.Category;
import com.example.repositories.CategoryRepository;

import java.util.List;

public class CategoryService {
  public List<Category> getAllCategorys() {
    CategoryRepository categoryRepository = new CategoryRepository();
    return categoryRepository.getAllCategories();
  }
  
  public int getOrInsertCategory(String categoryName) {
    CategoryRepository categoryRepository = new CategoryRepository();
    Category existingCat = categoryRepository.findByName(categoryName);
    if (existingCat != null) {
      return existingCat.getCategoryId();
    }
    Category newCat = new Category();
    newCat.setCategoryName(categoryName);
    categoryRepository.insertCategory(newCat);
    return categoryRepository.findByName(categoryName).getCategoryId();
  }
  
  public void insertCategory(Category c) {
    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.insertCategory(c);
  }
  
  public void updateCategory(Category c) {
    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.updateCategory(c);
  }
  
  public void deleteCategory(int id) {
    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.deteleCategory(id);
  }
  
  public boolean hasProduct(int categoryId) {
    CategoryRepository categoryRepository = new CategoryRepository();
    return categoryRepository.hasProduct(categoryId);
  }
}
