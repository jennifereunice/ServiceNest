package com.servicenest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.servicenest.model.User;
import com.servicenest.model.VendorDetails;
import com.servicenest.service.VendorProfileService;

@RestController
@RequestMapping("/vendor/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class VendorProfileController {

    @Autowired
    private VendorProfileService vendorProfileService;

    @GetMapping
    public ResponseEntity<VendorDetails> getProfile(@AuthenticationPrincipal User user) {
        VendorDetails profile = vendorProfileService.getProfile(user.getId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public ResponseEntity<VendorDetails> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody VendorDetails updated
    ) {
        VendorDetails profile = vendorProfileService.updateProfile(user.getId(), updated);
        return ResponseEntity.ok(profile);
    }
}
