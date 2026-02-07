package com.servicenest.service;

import com.servicenest.model.*;
import com.servicenest.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepo;
    private final VendorDetailsRepository vendorRepo;

    public ServiceRequestService(ServiceRequestRepository requestRepo,
                                 VendorDetailsRepository vendorRepo) {
        this.requestRepo = requestRepo;
        this.vendorRepo = vendorRepo;
    }

    // Vendor views requests by status
    public List<ServiceRequest> getVendorRequests(Long vendorId, String status) {
        if (status == null) {
            return requestRepo.findByVendorId(vendorId);
        }
        return requestRepo.findByVendorIdAndStatus(vendorId, status);
    }

    // Accept request
    public void acceptRequest(Long requestId) {
        ServiceRequest req = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("ACCEPTED");
        requestRepo.save(req);
    }

    // Reject request
    public void rejectRequest(Long requestId) {
        ServiceRequest req = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");
        requestRepo.save(req);
    }

    // Complete job
    public void completeRequest(Long requestId) {
        ServiceRequest req = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("COMPLETED");
        requestRepo.save(req);
    }
}
