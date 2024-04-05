package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;



public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmailAndPassword(String email, String password);
	
	@Query("SELECT u.username FROM User u WHERE u.email = :email")
	String findUsernameByEmail(String email);
}
