package com.controllers;

import com.modals.Category;
import com.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    @Qualifier("categories")
    private CategoryService categoryService;

    @GetMapping("/getCategories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("/addCategories")
    public Category addcategories(@RequestBody Category category){
        return categoryService.addCategories(category);
    }

    @DeleteMapping("/deleteCategories")
    public Category deleteCategory(@RequestBody Category category){
        return categoryService.deleteCategory(category);
    }
}
