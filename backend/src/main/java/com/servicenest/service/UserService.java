package com.servicenest.service;

import com.servicenest.dto.AuthResponse;
import com.servicenest.dto.LoginRequest;
import com.servicenest.dto.RegisterRequest;
import com.servicenest.model.User;
import com.servicenest.repository.UserRepository;
import com.servicenest.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register user (used by controller directly)
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole() == null ? "CUSTOMER" : user.getRole().toUpperCase());
        return userRepository.save(user);
    }

    // Validate user login
//    public User validateUser(String email, String password) {
//    	
//        User u = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
//
//        if (!passwordEncoder.matches(password, u.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }
//        return u;
//    }
    public User validateUser(String email, String password) {

        System.out.println("🔐 Login attempt started");
        System.out.println("➡️ Email entered: " + email);
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("❌ User not found with email: " + email);
                    return new RuntimeException("Invalid email or password");
                });

        System.out.println("✅ User found: " + user.getEmail());
        System.out.println("➡️ Stored (hashed) password: " + user.getPassword());
        System.out.println("➡️ Entered password (raw): " + password);

        boolean match = passwordEncoder.matches(password, user.getPassword());
        System.out.println("🔎 Password match result: " + match);

        if (!match) {
            System.out.println("❌ Password mismatch");
            throw new RuntimeException("Invalid email or password");
        }

        System.out.println("✅ Login successful for userId: " + user.getId());
        System.out.println("➡️ Role: " + user.getRole());
        System.out.println("➡️ Status: " + user.getStatus());

        return user;
    }

}
