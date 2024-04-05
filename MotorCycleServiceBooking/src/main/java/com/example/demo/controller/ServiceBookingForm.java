package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.BikeManufactureCompany;
import com.example.demo.model.BikeModel;
import com.example.demo.model.ServiceBooking;
import com.example.demo.repository.BikeManufactureCompanyRepository;
import com.example.demo.repository.BikeModelRepository;
import com.example.demo.repository.ServiceBookingRepository;

@Controller
public class ServiceBookingForm {

	@Autowired
	private BikeManufactureCompanyRepository bmcRepo;
	@Autowired
	private BikeModelRepository bmRepo;
	
	@Autowired
	private ServiceBookingRepository sbRepo;
	
	
	
	
	@GetMapping("/serviceBookingForm")
	
	public String serviveBookingForm(Model model){
		
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		 
		return "serviceBookingForm.html";
	}
	
	@PostMapping("/serviceBook")
	public String serviceBook(Model model, @ModelAttribute ServiceBooking sb){
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		
		sbRepo.save(sb);
		
		return "serviceBookingForm.html";
		
	}
	
}
