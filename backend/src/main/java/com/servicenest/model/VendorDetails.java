package com.servicenest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendor_details")
@Getter @Setter @NoArgsConstructor
public class VendorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // business fields
    private String businessName;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getGovtIdNumber() {
		return govtIdNumber;
	}

	public void setGovtIdNumber(String govtIdNumber) {
		this.govtIdNumber = govtIdNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String serviceType;      // plumber, electrician…
    private String govtIdNumber;
    private String address;
    private String city;
    private Integer experienceYears;

    // Vendor status
    private String approvalStatus = "PENDING";  // ADMIN_APPROVED / REJECTED

 // VendorDetails.java
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
