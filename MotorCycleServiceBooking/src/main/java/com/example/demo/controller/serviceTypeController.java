package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ServiceSubCategory;
import com.example.demo.model.ServiceType;
import com.example.demo.repository.ServiceSubCategoryRepository;
import com.example.demo.repository.ServiceTypeRepository;

@Controller
public class serviceTypeController {
	@Autowired
	private ServiceTypeRepository stRepo;
	@Autowired
	private ServiceSubCategoryRepository sscRepo;
	
	
	@GetMapping("/addServiceType")
	public String serviceType(){
		return "addServiceType.html";
	}
	
	@PostMapping("/addServiceTypeForm")
	public String addServiceType(@ModelAttribute ServiceSubCategory ssc, @RequestParam("serviceName") String serviceName, @RequestParam("serviceType") String serviceType, @RequestParam("price") float price ){
		
		Optional<ServiceType> existingServiceType = stRepo.findByServiceType(serviceType);
		
		
		ServiceType stype = new ServiceType();
		if(existingServiceType.isPresent()) {
			stype = existingServiceType.get();
		}else {
			stype = new ServiceType();
			stype.setServiceType(serviceType);
			stRepo.save(stype);
		}
		
		ServiceSubCategory subCat = new ServiceSubCategory();
		subCat.setType(stype);
		subCat.setServiceName(serviceName);
		subCat.setPrice(price);
		sscRepo.save(subCat);
		
		
		
		return "addServiceType.html";
	}
	
	

}
