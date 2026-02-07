package com.servicenest.controller;

import com.servicenest.model.VendorService;
import com.servicenest.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/services")
@CrossOrigin(origins = "http://localhost:5173")

public class UserServiceController {

    private final ServiceService serviceService;

    public UserServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // View all services
    @GetMapping
    public List<VendorService> getAllServices() {
        return serviceService.getAllAvailableServices();
    }
}
