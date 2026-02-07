package com.servicenest.controller;

import com.servicenest.model.ServiceRequest;
import com.servicenest.service.ServiceRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class VendorRequestController {

    private final ServiceRequestService requestService;

    public VendorRequestController(ServiceRequestService requestService) {
        this.requestService = requestService;
    }

    // View requests (Pending / Accepted / Completed)
    @GetMapping("/{vendorId}")
    public List<ServiceRequest> getRequests(
            @PathVariable Long vendorId,
            @RequestParam(required = false) String status) {

        return requestService.getVendorRequests(vendorId, status);
    }

    @PutMapping("/{id}/accept")
    public void accept(@PathVariable Long id) {
        requestService.acceptRequest(id);
    }

    @PutMapping("/{id}/reject")
    public void reject(@PathVariable Long id) {
        requestService.rejectRequest(id);
    }

    @PutMapping("/{id}/complete")
    public void complete(@PathVariable Long id) {
        requestService.completeRequest(id);
    }
}
