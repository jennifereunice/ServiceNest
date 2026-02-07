package com.servicenest.service;

import com.servicenest.model.VendorService;
import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.repository.VendorServiceRepository;
import com.servicenest.repository.UserRepository;
import com.servicenest.repository.VendorDetailsRepository;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ THIS MUST BE org.springframework.stereotype.Service
public class ServiceService {

    private final VendorServiceRepository serviceRepo;
    private final UserRepository userRepo;
    private final VendorDetailsRepository vendorRepo;

    public ServiceService(VendorServiceRepository serviceRepo,
                          UserRepository userRepo,
                          VendorDetailsRepository vendorRepo) {
        this.serviceRepo = serviceRepo;
        this.userRepo = userRepo;
        this.vendorRepo = vendorRepo;
    }

    public VendorService addService(Long userId, VendorService service) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"VENDOR".equals(user.getRole()))
            throw new RuntimeException("Only vendors allowed");

        VendorDetails vendor = vendorRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor details not found"));

        if (!"APPROVED".equals(vendor.getApprovalStatus()))
            throw new RuntimeException("Vendor not approved");

        service.setVendor(vendor);
        return serviceRepo.save(service);
    }
    
    public VendorService updateService(
            Long vendorDetailsId,
            Long serviceId,
            VendorService updated
    ) {
        VendorService service = serviceRepo
                .findByIdAndVendor_Id(serviceId, vendorDetailsId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setTitle(updated.getTitle());
        service.setDescription(updated.getDescription());
        service.setPrice(updated.getPrice());
        service.setAvailable(updated.getAvailable());

        return serviceRepo.save(service);
    }


    public List<VendorService> getAllAvailableServices() {
        return serviceRepo.findByAvailableTrue();
    }

    public List<VendorService> getVendorServices(Long userId) {
		// 1. Get vendor details using userId
        VendorDetails vendor = vendorRepo
                .findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor details not found"));

        // 2. Fetch services for this vendor
        return serviceRepo.findByVendor(vendor);
    }

	

    
    
}
