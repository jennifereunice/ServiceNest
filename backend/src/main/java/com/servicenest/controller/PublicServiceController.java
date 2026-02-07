package com.servicenest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicenest.model.VendorService;
import com.servicenest.service.ServiceService;

@RestController
@RequestMapping("/public/services")

@CrossOrigin(origins = "http://localhost:5173")
public class PublicServiceController {
	private final ServiceService serviceService;
	
	public PublicServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
	
    @GetMapping
    public List<VendorService> getAllServices() {
        return serviceService.getAllAvailableServices();
    }
}
