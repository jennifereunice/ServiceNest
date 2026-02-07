package com.servicenest.dto;

public class VendorAdminDTO {
    private Long vendorId;
    private UserDTO user;
    private String businessName;
    private String serviceType;
    private Integer experienceYears;
    private String address;
    private String city;
    private String govtIdNumber;
    private String approvalStatus;
    private String rejectionReason;

    // Constructor
    public VendorAdminDTO(Long vendorId, UserDTO user, String businessName, String serviceType,
                          Integer exp, String address, String city,
                          String govtIdNumber, String approvalStatus) {
        this.vendorId = vendorId;
        this.user = user;
        this.businessName = businessName;
        this.serviceType = serviceType;
        this.experienceYears = exp;
        this.address = address;
        this.city = city;
        this.govtIdNumber = govtIdNumber;
        this.approvalStatus = approvalStatus;
    }

    // Getters & Setters
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getGovtIdNumber() { return govtIdNumber; }
    public void setGovtIdNumber(String govtIdNumber) { this.govtIdNumber = govtIdNumber; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
