package com.servicenest.repository;

import com.servicenest.model.ServiceRequest;
import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.model.VendorService;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByVendorIdAndStatus(Long vendorId, String status);

    List<ServiceRequest> findByVendorId(Long vendorId);
    List<ServiceRequest> findByUser(User user);

	boolean existsByUserIdAndServiceId(Long id, Long serviceId);
	List<ServiceRequest> findByService_VendorAndStatus(VendorDetails vendor, String status);

	List<ServiceRequest> findByService_Vendor(VendorDetails vendor);


}



