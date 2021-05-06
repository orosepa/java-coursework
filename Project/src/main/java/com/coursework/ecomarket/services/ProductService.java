package com.coursework.ecomarket.services;

import com.coursework.ecomarket.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void addProduct(Product product);

    public List<Product> listProduct();

    public Optional<Product> getProductById(long productId);

    public List<Product> findByCategory(long categoryId);

    public void deleteProduct(long productId);

}

