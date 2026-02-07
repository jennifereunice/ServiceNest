package com.servicenest.repository;

import com.servicenest.model.VendorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendorDetailsRepository extends JpaRepository<VendorDetails, Long> {

    Optional<VendorDetails> findByUserId(Long userId);

    List<VendorDetails> findByApprovalStatus(String approvalStatus);
}
