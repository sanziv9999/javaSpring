package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.StockAndProductsRepository;

@Controller
public class stockAndProductController {

	
	
	@Autowired
	private StockAndProductsRepository sapRepo;
	
	
	@GetMapping("StockManagement")
	public String StockManagement() {
		
		
		return "Stocks.html";
	}
	

}
