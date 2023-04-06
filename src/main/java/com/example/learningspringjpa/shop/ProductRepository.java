package com.example.learningspringjpa.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategory(Category category);
    @Query("select p.category from Product p")
    Set<Category> categories();

}
