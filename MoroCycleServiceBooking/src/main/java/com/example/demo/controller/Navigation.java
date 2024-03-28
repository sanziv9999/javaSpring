package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Navigation {

	
	@GetMapping("/home")
	public String home(){
		return "index.html";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about.html";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service.html";
		
	}
	
	@GetMapping("/contact")
	public String contact(){
		return "contact.html";
	}
	
	
	@GetMapping("/booking")
	public String booking(){
		return "booking.html";
	}
	
	@GetMapping("/team")
	public String team(){
		return "team.html";
	}
	
	@GetMapping("/testimonial")
	public String testimonial(){
		return "testimonial.html";
	}
	
	
	
	
}
