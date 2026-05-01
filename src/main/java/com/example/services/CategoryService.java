package com.example.services;

import com.example.models.Category;
import com.example.repositories.CategoryRepository;

import java.util.List;

public class CategoryService {
  public List<Category> getAllCategorys() {
    CategoryRepository categoryRepository = new CategoryRepository();
    return categoryRepository.getAllCategories();
  }
}
