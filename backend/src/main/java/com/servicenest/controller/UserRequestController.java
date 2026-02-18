package com.servicenest.controller;

import com.servicenest.model.ServiceRequest;
import com.servicenest.model.User;
import com.servicenest.service.RequestService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class UserRequestController {

    private final RequestService requestService;

    public UserRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // ✅ Create request for a service
    @PostMapping("/{serviceId}")
    public ServiceRequest createRequest(@PathVariable Long serviceId,
                                        Authentication authentication) {

        return requestService.createRequest(serviceId, authentication);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable Long id, Authentication authentication) {

        requestService.deleteRequest(id, authentication);
        return "Request deleted successfully";
    }


    // ✅ Get logged-in user's requests
    @GetMapping
    public List<ServiceRequest> getMyRequests(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return requestService.getRequestsByUser(user);
    }

}
