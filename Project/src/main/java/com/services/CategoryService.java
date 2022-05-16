package com.services;

import com.modals.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category addCategories(Category  category);
    Category deleteCategory(Category category);
}
