package com.servicenest.dto;

import com.servicenest.model.VendorService;

public class ServiceWithVendorDTO {
    private Long serviceId;
    private String title;
    private String description;
    private Double price;
    private String category;
    private Boolean available;
    
    // Vendor info
    private Long vendorId;
    private String businessName;
    private String city;
    private String approvalStatus;

    public ServiceWithVendorDTO(VendorService service) {
        this.serviceId = service.getId();
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.price = service.getPrice();
        this.category = service.getCategory();
        this.available = service.getAvailable();

        this.vendorId = service.getVendor().getId();
        this.businessName = service.getVendor().getBusinessName();
        this.city = service.getVendor().getCity();
        this.approvalStatus = service.getVendor().getApprovalStatus();
    }

    // getters and setters
}
