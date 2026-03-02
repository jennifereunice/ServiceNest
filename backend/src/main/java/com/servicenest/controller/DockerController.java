package com.servicenest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DockerController {

    @GetMapping("/api/info")
    public ResponseEntity<Map<String, String>> getInfo() {
        return ResponseEntity.ok(
                Map.of("email", "jennifereunice@gmail.com")
        );
    }
}