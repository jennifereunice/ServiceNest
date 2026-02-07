package com.servicenest.security;

import com.servicenest.model.User;
import com.servicenest.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // -------------------------
        // 1️⃣ Handle CORS preflight
        // -------------------------
    	if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    	    filterChain.doFilter(request, response);
    	    return;
    	}


    	String path = request.getServletPath();

        System.out.println("➡️ Request URI: " + path);

        // -------------------------
        // 2️⃣ Skip JWT filter for public endpoints
        // -------------------------
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // -------------------------
        // 3️⃣ Extract JWT token
        // -------------------------
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = jwtService.parseToken(token).getBody();
            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            System.out.println("🔥 JWT parsed:");
            System.out.println("   Email: " + email);
            System.out.println("   Role from token: " + role);

            // -------------------------
            // 4️⃣ Load user and set Spring Security context
            // -------------------------
            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null && role != null) {
                // Ensure role has "ROLE_" prefix for Spring Security
                String finalRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;

                // 👇 SET THE FULL USER AS PRINCIPAL
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user,  // <- Full User object
                                null,
                                List.of(new SimpleGrantedAuthority(finalRole))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("✅ Authentication set for: " + finalRole);
            }


        } catch (Exception ex) {
            System.out.println("❌ Invalid token: " + ex.getMessage());
            // Security context remains empty -> user is unauthenticated
        }

        // -------------------------
        // 5️⃣ Continue filter chain
        // -------------------------
        filterChain.doFilter(request, response);
    }
}
