package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BikeModel;

public interface BikeModelRepository extends JpaRepository<BikeModel, Integer> {

}
