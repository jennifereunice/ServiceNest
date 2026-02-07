package com.servicenest.model;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
@Getter @Setter @NoArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who booked
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // What service
    @ManyToOne
    @JoinColumn(name = "service_id")
    private VendorService service;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VendorService getService() {
		return service;
	}

	public void setService(VendorService service) {
		this.service = service;
	}

	public VendorDetails getVendor() {
		return vendor;
	}

	public void setVendor(VendorDetails vendor) {
		this.vendor = vendor;
	}

	public LocalDateTime getScheduledAt() {
		return scheduledAt;
	}

	public void setScheduledAt(LocalDateTime scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Assigned vendor
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private VendorDetails vendor;

    private LocalDateTime scheduledAt;
    private String address;

    private String status; 
    // PENDING, ACCEPTED, REJECTED, COMPLETED
}
