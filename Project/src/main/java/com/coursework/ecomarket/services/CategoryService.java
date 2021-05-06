package com.coursework.ecomarket.services;

import com.coursework.ecomarket.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public void addCategory(Category category);

    public List<Category> listCategory();

    public void deleteCategory(long categoryId);

    public void updateCategory(Category category);

    public Optional<Category> getCategory(long categoryId);

}

