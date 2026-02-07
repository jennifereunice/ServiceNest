package com.servicenest.controller;

import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.model.VendorService;
import com.servicenest.repository.VendorDetailsRepository;
import com.servicenest.service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor/services")
@CrossOrigin(origins = "http://localhost:5173")

public class VendorServiceController {

    private final ServiceService serviceService;

    public VendorServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Add service
    @PostMapping("/{userId}")
    public VendorService addService(@PathVariable Long userId,
                              @RequestBody VendorService service) {
        return serviceService.addService(userId, service);
    }

    // View own services
    @GetMapping("/{userId}")
    public List<VendorService> getVendorServices(@PathVariable Long userId) {
        return serviceService.getVendorServices(userId);
    }
    
    

    @Autowired
    private VendorDetailsRepository vendorDetailsRepo;

    @PutMapping("/{serviceId}")
    public ResponseEntity<?> editService(
            @PathVariable Long serviceId,
            @RequestBody VendorService service,
            @AuthenticationPrincipal User user
    ) {
        VendorDetails vendorDetails = vendorDetailsRepo
                .findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Vendor profile not found"));

        return ResponseEntity.ok(
                serviceService.updateService(
                        vendorDetails.getId(),
                        serviceId,
                        service
                )
        );
    }
}
