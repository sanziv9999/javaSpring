package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.BikeManufactureCompany;
import com.example.demo.model.BikeModel;
import com.example.demo.repository.BikeManufactureCompanyRepository;
import com.example.demo.repository.BikeModelRepository;


@Controller
public class bikeModelController {
	
	@Autowired
	private BikeModelRepository bmRepo;
	
	@Autowired
	private BikeManufactureCompanyRepository bmcRepo;
	
	
	
	@GetMapping("/addbikemodel")
	public String bikemodel(){
		return "BikeModel.html";
	}
	@PostMapping("/bikemodelform")
	public String bikemodelform(@ModelAttribute BikeModel bm, @RequestParam("companyName") String companyName, @RequestParam("modelName") String modelName,@RequestParam("year") Integer year ){
		
		Optional<BikeManufactureCompany> existingCompanyOptional = bmcRepo.findByCompanyName(companyName);

	
		BikeManufactureCompany company = new BikeManufactureCompany();
		if (existingCompanyOptional.isPresent()) {
        company = existingCompanyOptional.get();
        } else {
        	
		  company = new BikeManufactureCompany();
	      company.setCompanyName(companyName);
	      bmcRepo.save(company); 
        }
		
		
		
		BikeModel model = new BikeModel();
		model.setCompany(company);
		model.setModelName(modelName);
		model.setYear(year);
		bmRepo.save(model);
		
		
		return "BikeModel.html";
	}
	

}
