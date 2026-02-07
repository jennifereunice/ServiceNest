package com.servicenest.repository;

import com.servicenest.model.VendorService;
import com.servicenest.model.VendorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorServiceRepository extends JpaRepository<VendorService, Long> {

    List<VendorService> findByVendor(VendorDetails vendor);

    List<VendorService> findByAvailableTrue();

    // ✅ FIXED METHOD NAME
    Optional<VendorService> findByIdAndVendor_Id(
            Long serviceId,
            Long vendorId
    );
}
