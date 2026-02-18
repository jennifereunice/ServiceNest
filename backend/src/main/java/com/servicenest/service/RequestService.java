package com.servicenest.service;

import com.servicenest.model.*;
import com.servicenest.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final ServiceRequestRepository requestRepo;
    private final VendorServiceRepository serviceRepo;
    private final UserRepository userRepo;

    public RequestService(ServiceRequestRepository requestRepo,
                          VendorServiceRepository serviceRepo,
                          UserRepository userRepo) {
        this.requestRepo = requestRepo;
        this.serviceRepo = serviceRepo;
        this.userRepo = userRepo;
    }
    public ServiceRequest createRequest(Long serviceId, Authentication authentication) {

        // Get the logged-in user
        User user = (User) authentication.getPrincipal();

        // Check if user has already requested this service
        boolean alreadyRequested =
                requestRepo.existsByUserIdAndServiceId(user.getId(), serviceId);

        if (alreadyRequested) {
            throw new RuntimeException("You have already requested this service.");
        }

        // Fetch the service
        com.servicenest.model.VendorService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        // 🔹 Create a new ServiceRequest
        ServiceRequest request = new ServiceRequest();
        request.setUser(user);              // User who requested
        request.setService(service);        // Service linked to vendor
        request.setStatus("PENDING");       // Default status

        // Optional: print for debugging
        System.out.println("Created request for user: " + user.getId()
                + " to service: " + service.getId()
                + " by vendor: " + service.getVendor().getId());

        // Save request
        return requestRepo.save(request);
    }

//    public ServiceRequest createRequest(Long serviceId, Authentication authentication) {
//
//        User user = (User) authentication.getPrincipal();
//
//        System.out.println("User ID: " + user.getId());
//        System.out.println("Service ID: " + serviceId);
//
//        boolean alreadyRequested =
//                requestRepo.existsByUserIdAndServiceId(user.getId(), serviceId);
//
//        System.out.println("Already requested? " + alreadyRequested);
//
//        if (alreadyRequested) {
//            throw new RuntimeException("You have already requested this service.");
//        }
//
//        com.servicenest.model.VendorService service = serviceRepo.findById(serviceId)
//                .orElseThrow(() -> new RuntimeException("Service not found"));
//
//        ServiceRequest request = new ServiceRequest();
//        request.setUser(user);
//        request.setService(service);
//        request.setStatus("PENDING");
//
//        return requestRepo.save(request);
//    }
    
    public void deleteRequest(Long requestId, Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        ServiceRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // 🔥 Make sure user owns this request
        if (!request.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this request");
        }

        requestRepo.delete(request);
    }






    public List<ServiceRequest> getRequestsByUser(User user) {
        return requestRepo.findByUser(user);
    }

	

}
