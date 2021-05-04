package com.coursework.ecomarket.repositories;

import com.coursework.ecomarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory_CategoryId(long CategoryId);

}

