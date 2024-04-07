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
import com.example.demo.model.ServiceSubCategory;
import com.example.demo.model.ServiceType;
import com.example.demo.model.User;
import com.example.demo.model.WorkStatus;
import com.example.demo.repository.BikeManufactureCompanyRepository;
import com.example.demo.repository.BikeModelRepository;
import com.example.demo.repository.ServiceBookingRepository;
import com.example.demo.repository.ServiceSubCategoryRepository;
import com.example.demo.repository.ServiceTypeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WorkStatusRepository;

@Controller
public class ServiceBookingForm {

	@Autowired
	private BikeManufactureCompanyRepository bmcRepo;
	@Autowired
	private BikeModelRepository bmRepo;
	
	@Autowired
	private ServiceBookingRepository sbRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private ServiceTypeRepository stRepo;
	
	@Autowired
	private ServiceSubCategoryRepository sscRepo;
	
	@Autowired
	private WorkStatusRepository wsRepo;
	
	
	
	
	
	
	
	
	@GetMapping("/serviceBookingForm")
	
	public String serviveBookingForm(Model model){
		
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		
		List<ServiceType> stList = stRepo.findAll();
		model.addAttribute("stList", stList );
		
		
		List<ServiceSubCategory> sscList = sscRepo.findAll();
		model.addAttribute("sscList", sscList);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
				
		
		 
		return "serviceBookingForm.html";
	}
	
	@PostMapping("/serviceBook")
	public String serviceBook(Model model, @ModelAttribute ServiceBooking sb){
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		
		List<ServiceType> stList = stRepo.findAll();
		model.addAttribute("stList", stList );
		
		
		List<ServiceSubCategory> sscList = sscRepo.findAll();
		model.addAttribute("sscList", sscList);
		
		
		
		sbRepo.save(sb);
		
		return "serviceBookingForm.html";
		
	}
	
	@GetMapping("serviceBookedPage")
	public String serviceBookedPage( Model model) {
		
		List<ServiceBooking> sbList = sbRepo.findAll();
		
		model.addAttribute("sbList",sbList);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
		
		return "serviceBookedPage.html";
	}
	
}
