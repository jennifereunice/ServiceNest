package com.servicenest.service;


import com.servicenest.dto.UserDTO;
import com.servicenest.dto.VendorAdminDTO;
import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.repository.UserRepository;
import com.servicenest.repository.VendorDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminVendorService {

    private final VendorDetailsRepository vendorDetailsRepository;
    private final UserRepository userRepository;

    public AdminVendorService(VendorDetailsRepository vendorDetailsRepository,
                              UserRepository userRepository) {
        this.vendorDetailsRepository = vendorDetailsRepository;
        this.userRepository = userRepository;
    }

    // -----------------------------
    // 1. VIEW ALL PENDING VENDORS
    // -----------------------------
    public List<VendorAdminDTO> getPendingVendors() {
        return vendorDetailsRepository.findByApprovalStatus("PENDING")
                .stream()
                .map(this::mapToAdminDTO)
                .toList();
    }

    // -----------------------------
    // 2. VIEW ALL APPROVED VENDORS
    // -----------------------------
    public List<VendorAdminDTO> getApprovedVendors() {
        return vendorDetailsRepository.findByApprovalStatus("APPROVED")
                .stream()
                .map(this::mapToAdminDTO)
                .toList();
    }

    // -----------------------------
    // 3. VIEW ALL REJECTED VENDORS
    // -----------------------------
    public List<VendorAdminDTO> getRejectedVendors() {
        return vendorDetailsRepository.findByApprovalStatus("REJECTED")
                .stream()
                .map(this::mapToAdminDTO)
                .toList();
    }

    // -----------------------------
    // 4. APPROVE VENDOR
    // -----------------------------
    public VendorAdminDTO approveVendor(Long vendorId) {

        VendorDetails vendor = vendorDetailsRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setApprovalStatus("APPROVED");
        vendorDetailsRepository.save(vendor);

        // Activate user
        User user = vendor.getUser();
        user.setStatus("ACTIVE");
        userRepository.save(user);

        return mapToAdminDTO(vendor);
    }

    // -----------------------------
    // 5. REJECT VENDOR
    // -----------------------------
    public VendorAdminDTO rejectVendor(Long vendorId) {

        VendorDetails vendor = vendorDetailsRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        vendor.setApprovalStatus("REJECTED");
        vendorDetailsRepository.save(vendor);

        return mapToAdminDTO(vendor);
    }

    // -----------------------------
    // COMMON MAPPER METHOD
    // -----------------------------
    private VendorAdminDTO mapToAdminDTO(VendorDetails v) {
        User u = v.getUser();

        UserDTO userDTO = new UserDTO(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPhone(),
                u.getRole(),
                u.getStatus()
        );

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
    }
}
