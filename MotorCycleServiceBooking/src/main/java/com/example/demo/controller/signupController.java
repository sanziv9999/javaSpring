package com.example.demo.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;



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
	public String login(@ModelAttribute User u, Model model,HttpSession session){
		if(uRepo.existsByEmailAndPassword(u.getEmail(), DigestUtils.sha3_256Hex(u.getPassword()))) {
			String username = uRepo.findUsernameByEmail(u.getEmail());
			if(username!=null) {
				session.setAttribute("username", u.getUsername());
				model.addAttribute("email",u.getEmail());
				session.setAttribute("username", username);
				System.out.println(session.getAttribute("username"));
				return "index.html";
			}
		
		
		}

		return "index.html";
	}
	
	@GetMapping("/adminDash")
	public String dashboard(@ModelAttribute User u, HttpSession session) {
		
	
		return "adminDash.html";
	}
	
	
	
	
	
	}
	
