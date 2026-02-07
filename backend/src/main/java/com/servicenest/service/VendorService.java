package com.servicenest.service;

import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.repository.UserRepository;
import com.servicenest.repository.VendorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.servicenest.dto.UserDTO;
import com.servicenest.dto.VendorAdminDTO;


import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorDetailsRepository vendorDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    // -----------------------------
    // 1. SAVE / UPDATE VENDOR DETAILS
    // -----------------------------
    public VendorDetails saveVendorDetails(Long userId, VendorDetails input) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        VendorDetails vendor;

        // 🔑 KEY FIX: check if vendor already exists
        var existingOpt = vendorDetailsRepository.findByUserId(userId);

        if (existingOpt.isPresent()) {
            vendor = existingOpt.get();   // UPDATE
        } else {
            vendor = new VendorDetails(); // INSERT (first time only)
            vendor.setUser(user);
            vendor.setApprovalStatus("PENDING");
        }

        // copy fields (common)
        vendor.setBusinessName(input.getBusinessName());
        vendor.setServiceType(input.getServiceType());
        vendor.setGovtIdNumber(input.getGovtIdNumber());
        vendor.setAddress(input.getAddress());
        vendor.setCity(input.getCity());
        vendor.setExperienceYears(input.getExperienceYears());

        return vendorDetailsRepository.save(vendor);
    }


    // -----------------------------
    // 2. GET VENDOR BY USER ID
    // -----------------------------
    public VendorDetails getVendorByUserId(Long userId) {
        return vendorDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));
    }

    // -----------------------------
    // 3. ADMIN APPROVE VENDOR
    // -----------------------------
    public VendorAdminDTO approveVendor(Long vendorId) {
        VendorDetails v = vendorDetailsRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));

        v.setApprovalStatus("APPROVED");
        vendorDetailsRepository.save(v);

        // Update user status as well
        User u = v.getUser();
        u.setStatus("ACTIVE");  // now user's status reflects approval
        userRepository.save(u);

        UserDTO userDTO = new UserDTO(u.getId(), u.getName(), u.getEmail(),
                                      u.getPhone(), u.getRole(), u.getStatus());

        return new VendorAdminDTO(v.getId(), userDTO, v.getBusinessName(), v.getServiceType(),
                                  v.getExperienceYears(), v.getAddress(), v.getCity(),
                                  v.getGovtIdNumber(), v.getApprovalStatus());
    }


    // -----------------------------
    // 4. ADMIN REJECT VENDOR
    // -----------------------------
    public VendorDetails rejectVendor(Long vendorId, String reason) {
        VendorDetails vendor = vendorDetailsRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor Not Found"));

        vendor.setApprovalStatus("REJECTED"); // add this field in VendorDetails entity
        return vendorDetailsRepository.save(vendor);
    }

    // -----------------------------
    // 5. ADMIN VIEW ALL VENDORS
    // -----------------------------
    public List<VendorAdminDTO> getAllVendorsForAdmin() {
        return vendorDetailsRepository.findAll().stream().map(v -> {
            User u = v.getUser();
            UserDTO userDTO = new UserDTO(u.getId(), u.getName(), u.getEmail(),
                                          u.getPhone(), u.getRole(), u.getStatus());
            return new VendorAdminDTO(
                    v.getId(),
                    userDTO,
                    v.getBusinessName(),
                    v.getServiceType(),
                    v.getExperienceYears(),
                    v.getAddress(),
                    v.getCity(),
                    v.getGovtIdNumber(),
                    v.getApprovalStatus()
            );
        }).toList();
    }


    // -----------------------------
    // 6. ADMIN VIEW PENDING VENDORS
    // -----------------------------
    public List<VendorDetails> getPendingVendors() {
        return vendorDetailsRepository.findByApprovalStatus("PENDING");
    }

    // -----------------------------
    // 7. ADMIN VIEW APPROVED VENDORS
    // -----------------------------
    public List<VendorDetails> getApprovedVendors() {
        return vendorDetailsRepository.findByApprovalStatus("APPROVED");
    }

    // -----------------------------
    // 8. ADMIN VIEW REJECTED VENDORS
    // -----------------------------
    public List<VendorDetails> getRejectedVendors() {
        return vendorDetailsRepository.findByApprovalStatus("REJECTED");
    }
}
