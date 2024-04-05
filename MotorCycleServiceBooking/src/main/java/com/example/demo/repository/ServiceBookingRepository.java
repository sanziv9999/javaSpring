package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ServiceBooking;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Integer> {

}
