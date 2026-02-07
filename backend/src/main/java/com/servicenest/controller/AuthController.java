package com.servicenest.controller;
import java.util.Map;

import javax.management.relation.Role;

import java.util.HashMap;

import com.servicenest.dto.VendorAdminDTO;
import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.repository.VendorDetailsRepository;
import com.servicenest.security.JwtService;
import com.servicenest.service.UserService;
import com.servicenest.service.VendorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")

public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private VendorDetailsRepository VendorDetailsRepository;

    @Autowired
    private VendorService vendorService;
    
    @Autowired
    private JwtService jwtService; 

    // -----------------------------
    // 1. USER / VENDOR REGISTRATION
    // -----------------------------
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // 1. Save user
        User savedUser = userService.registerUser(user);

        // 2. If role is VENDOR, create empty vendor details automatically
        if ("VENDOR".equalsIgnoreCase(savedUser.getRole())) {
            VendorDetails vendorDetails = new VendorDetails();
            vendorDetails.setUser(savedUser);
            vendorService.saveVendorDetails(savedUser.getId(), vendorDetails);
        }

        return savedUser;
    }

    // -----------------------------
    // 2. USER LOGIN
    // -----------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userService.validateUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        
        System.out.println("the status is "+user.getStatus());
        System.out.println("the role is "+user.getRole());

        // Generate JWT token
        String token = jwtService.generateToken(user);
        
        
        
        String status = user.getStatus(); // default

        if (user.getRole().equals("VENDOR")) {
            VendorDetails vendor =
                VendorDetailsRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));

            status = vendor.getApprovalStatus(); // APPROVED / PENDING
        }

        // Return token + role + status
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("status", status);
        response.put("id", user.getId());

        return ResponseEntity.ok(response);
    }


    // -----------------------------
    // 3. SAVE / UPDATE VENDOR DETAILS
    // -----------------------------
    @PostMapping("/vendor/{userId}/details")
    public VendorDetails saveVendorDetails(
            @PathVariable Long userId,
            @RequestBody VendorDetails vendorDetails
    ) {
        return vendorService.saveVendorDetails(userId, vendorDetails);
    }

    // -----------------------------
    // 4. GET VENDOR DETAILS BY USER ID
    // -----------------------------
    @GetMapping("/vendor/{userId}")
    public VendorDetails getVendorDetails(@PathVariable Long userId) {
        return vendorService.getVendorByUserId(userId);
    }

    // -----------------------------
    // 5. ADMIN APPROVES VENDOR
    // -----------------------
    
 // -----------------------------
 // APPROVE VENDOR
 // -----------------------------
 @PutMapping("/admin/vendor/{vendorId}/approve")
 public VendorAdminDTO approveVendor(@PathVariable Long vendorId) {
     return vendorService.approveVendor(vendorId);
 }

 // -----------------------------
 // REJECT VENDOR
 // -----------------------------
 @PutMapping("/admin/vendor/{vendorId}/reject")
 public VendorDetails rejectVendor(
         @PathVariable Long vendorId,
         @RequestBody(required = false) String reason
 ) {
     return vendorService.rejectVendor(vendorId, reason);
 }

 // -----------------------------
 // GET ALL VENDORS
 // -----------------------------
 @GetMapping("/admin/vendors")
 public List<VendorAdminDTO> getAllVendors() {
     return vendorService.getAllVendorsForAdmin();
 }


 // -----------------------------
 // GET VENDORS BY STATUS
 // -----------------------------
 @GetMapping("/admin/vendors/status/{status}")
 public List<VendorDetails> getVendorsByStatus(@PathVariable String status) {
     switch (status.toUpperCase()) {
         case "PENDING":
             return vendorService.getPendingVendors();
         case "APPROVED":
             return vendorService.getApprovedVendors();
         case "REJECTED":
             return vendorService.getRejectedVendors();
         default:
             throw new RuntimeException("Invalid status: " + status);
     }
 }


}
