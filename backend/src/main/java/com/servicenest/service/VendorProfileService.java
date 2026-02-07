package com.servicenest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.repository.UserRepository;
import com.servicenest.repository.VendorDetailsRepository;

@Service
public class VendorProfileService {

    @Autowired
    private VendorDetailsRepository vendorRepo;

    @Autowired
    private UserRepository userRepo;

    public VendorDetails getProfile(Long userId) {
        return vendorRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor profile not found"));
    }

    public VendorDetails updateProfile(Long userId, VendorDetails updated) {
        VendorDetails existing = vendorRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor profile not found"));

        existing.setBusinessName(updated.getBusinessName());
        existing.setAddress(updated.getAddress());
        existing.setCity(updated.getCity());
        existing.setExperienceYears(updated.getExperienceYears());
        existing.setServiceType(updated.getServiceType());
        existing.setGovtIdNumber(updated.getGovtIdNumber());

        return vendorRepo.save(existing);
    }
}
