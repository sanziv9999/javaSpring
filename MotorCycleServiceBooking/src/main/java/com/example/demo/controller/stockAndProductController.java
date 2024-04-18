package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BikeManufactureCompany;
import com.example.demo.model.BikeModel;
import com.example.demo.model.StockAndProducts;
import com.example.demo.repository.BikeManufactureCompanyRepository;
import com.example.demo.repository.BikeModelRepository;
import com.example.demo.repository.BikePartsRepository;
import com.example.demo.repository.StockAndProductsRepository;

@Controller
public class stockAndProductController {

    @Autowired
    private StockAndProductsRepository sapRepo;
	@Autowired
	private BikeManufactureCompanyRepository bmcRepo;
	@Autowired
	private BikeModelRepository bmRepo;
	
	@Autowired   
	private BikePartsRepository bpRepo;
	
    

    @GetMapping("/StockManagement")
    public String stockManagement(Model model) {
    	List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		
		List<StockAndProducts> spList = sapRepo.findAll();
		model.addAttribute("spList", spList);
        return "Stocks.html";
    }

    @PostMapping("/stockAdding")
    public String addStock(@RequestParam("itemPic") MultipartFile itemPic,
                           @RequestParam("itemName") String itemName,
                           @RequestParam("brand") String brand,
                           @RequestParam("quantity") int quantity,
                           @RequestParam("unitPrice") int unitPrice,
                           @RequestParam("purchaseDate") String purchaseDate,
                           @RequestParam("description") String desc, Model model) {

        try {
            StockAndProducts sp = new StockAndProducts();
            sp.setItemName(itemName);
            sp.setBrand(brand);


            sp.setQuantity(quantity);
            sp.setUnitPrice(unitPrice);
            sp.setDescription(desc);
            sp.setPurchasedDate(purchaseDate);
            sp.setItemPic(itemPic.getOriginalFilename());

            StockAndProducts saved = sapRepo.save(sp);

            // Save the image file
            if (saved != null) {
                try {
                    File saveDir = new ClassPathResource("static/assets").getFile();
                    Path imgPath = Paths.get(saveDir.getAbsolutePath(), itemPic.getOriginalFilename());
                    Files.copy(itemPic.getInputStream(), imgPath, StandardCopyOption.REPLACE_EXISTING);

                    // Set the itemPic property with the file path
                    sp.setItemPic(imgPath.toString());

                    System.out.println("Item Picture saved to: " + imgPath);
                    List<BikeModel> bmList = bmRepo.findAll();
            		model.addAttribute("bmList", bmList);
            		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
            		model.addAttribute("bmcList", bmcList);
            		
            		
            		List<StockAndProducts> spList = sapRepo.findAll();
            		model.addAttribute("spList", spList);
                    return "Stocks.html";
                } catch (IOException e) {
                    e.printStackTrace();
                    List<BikeModel> bmList = bmRepo.findAll();
            		model.addAttribute("bmList", bmList);
            		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
            		model.addAttribute("bmcList", bmcList);
            		
            		
            		List<StockAndProducts> spList = sapRepo.findAll();
            		model.addAttribute("spList", spList);
                    return "Stocks.html"; // Some error page
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<BikeModel> bmList = bmRepo.findAll();
		model.addAttribute("bmList", bmList);
		List<BikeManufactureCompany> bmcList=bmcRepo.findAll();
		model.addAttribute("bmcList", bmcList);
		
		
		List<StockAndProducts> spList = sapRepo.findAll();
		model.addAttribute("spList", spList);
        return "Stocks.html"; // Some error page
    }
    
    
    @GetMapping("/stockEdit/{id}")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
