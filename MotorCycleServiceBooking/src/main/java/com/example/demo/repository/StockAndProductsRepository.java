package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.StockAndProducts;

public interface StockAndProductsRepository extends JpaRepository<StockAndProducts, Integer> {

}
