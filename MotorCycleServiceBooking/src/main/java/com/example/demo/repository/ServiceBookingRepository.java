package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.ServiceBooking;

public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Integer> {

	List<ServiceBooking> findByEmail(String email);
}
