package com.example.demo.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@Controller
public class signupController {
	@Autowired
	private UserRepository uRepo;
	
	
	@GetMapping("/")
	public String landing(){
		return "index.html";
	}
	
	@PostMapping("/register")
	public String signup(@ModelAttribute User u){
		String hashPwd =DigestUtils.sha3_256Hex(u.getPassword());
		u.setPassword(hashPwd);
		uRepo.save(u);
		return "index.html";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute User u, Model model){
		if(uRepo.existsByEmailAndPassword(u.getEmail(), DigestUtils.sha3_256Hex(u.getPassword()))) {
			return "admindash.html";
		}

		return "index.html";
	}
		
	
	}
	
