package com.servicenest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicenest.dto.VendorAdminDTO;
import com.servicenest.service.AdminVendorService;
@RestController
@RequestMapping("/admin/vendors")

@CrossOrigin(origins = "http://localhost:5173")
public class AdminVendorController {

    private final AdminVendorService adminVendorService;

    public AdminVendorController(AdminVendorService adminVendorService) {
        this.adminVendorService = adminVendorService;
    }

    @GetMapping("/pending")
    public List<VendorAdminDTO> pending() {
        return adminVendorService.getPendingVendors();
    }

    @PutMapping("/{id}/approve")
    public VendorAdminDTO approve(@PathVariable Long id) {
        return adminVendorService.approveVendor(id);
    }

    @PutMapping("/{id}/reject")
    public VendorAdminDTO reject(@PathVariable Long id) {
        return adminVendorService.rejectVendor(id);
    }
}


