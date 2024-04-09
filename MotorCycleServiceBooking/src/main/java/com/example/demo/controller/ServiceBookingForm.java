package com.example.demo.controller;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("userBookedView")
	public String userBookedView( Model model) {
		
		List<ServiceBooking> sbList = sbRepo.findAll();
		
		model.addAttribute("sbList",sbList);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
		
		return "userBookedView.html";
	}
	
	@GetMapping("/cancelBooking/{id}")
	public String calcelBooking(Model model, @PathVariable int id) {
		
		try{
			ServiceBooking sb = sbRepo.getById(id);
			if(sb != null) {
				sb.setStatus("Cancelled");
				sbRepo.save(sb);
			}else {
				System.out.println("Booked id not found");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ServiceBooking> sbList = sbRepo.findAll();
		model.addAttribute("sbList",sbList);
		return "userBookedView.html";
	}
	
	@GetMapping("/deleteData/{id}")
	
	public String deleteData(Model model, @PathVariable int id){
		sbRepo.deleteById(id);
		
		List<ServiceBooking> sbList = sbRepo.findAll();
		
		model.addAttribute("sbList",sbList);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
		
		model.addAttribute("sbList",sbList);
		return "serviceBookedPage.html";
	}
	
	

	@GetMapping("/statusEdit/{id}")
	public String statusEdit(@PathVariable int id , Model model) {
		
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		
		List<ServiceType> stList = stRepo.findAll();
		model.addAttribute("stList", stList );
		
		
		List<ServiceSubCategory> sscList = sscRepo.findAll();
		model.addAttribute("sscList", sscList);
		
		ServiceBooking sb = sbRepo.getById(id);
		model.addAttribute("sb", sb);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
		return "adminEditForm.html";
		
	}
	
	@PostMapping("editBookedService")
	public String editBookedService(Model model ,@ModelAttribute ServiceBooking sb) { 
		
		sbRepo.save(sb);
		
		List<ServiceBooking> sbList = sbRepo.findAll();
		
		model.addAttribute("sbList",sbList);
		
		List<WorkStatus> wsList = wsRepo.findAll();
		model.addAttribute("wsList", wsList);
		
		return "serviceBookedPage.html";
	}
	
	
	
	
	
	
	
}