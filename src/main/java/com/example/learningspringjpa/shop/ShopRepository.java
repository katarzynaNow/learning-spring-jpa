package com.example.learningspringjpa.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Product, Integer> {
}
