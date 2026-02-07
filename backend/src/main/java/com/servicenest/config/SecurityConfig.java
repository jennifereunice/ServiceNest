package com.servicenest.config;

import com.servicenest.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // -------------------------
    // PASSWORD ENCODER
    // -------------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // -------------------------
    // SECURITY FILTER CHAIN
    // -------------------------
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .logout(logout -> logout.disable())

            .authorizeHttpRequests(auth -> auth

                // ---------- PUBLIC ----------
                .requestMatchers(
                    "/auth/register",
                    "/auth/login",
                    "/auth/vendor/*/details"
                ).permitAll()

                // ---------- ADMIN (JWT REQUIRED) ----------
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // ---------- VENDOR (JWT REQUIRED) ----------
                .requestMatchers("/vendor/**").hasRole("VENDOR")

                // ---------- USER ----------
                .requestMatchers("/user/**").hasRole("USER")

                // ---------- EVERYTHING ELSE ----------
                .anyRequest().authenticated()
            );

        // JWT FILTER
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // -------------------------
    // AUTH MANAGER
    // -------------------------
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }
}
