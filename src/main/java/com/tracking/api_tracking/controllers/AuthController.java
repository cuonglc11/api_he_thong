package com.tracking.api_tracking.controllers;

import com.tracking.api_tracking.dto.*;
import com.tracking.api_tracking.models.Employees;
import com.tracking.api_tracking.response.ApiResponse;
import com.tracking.api_tracking.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginDto dto)
    {
        String token  = authService.login(dto);
        ApiResponse<Object> rs = ApiResponse.builder().status(true).message("Login Success").data(token).build();
        return ResponseEntity.ok(rs);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> registerUser(@Valid @RequestBody RegisterDto dto) {
        Employees employees = authService.registerUser(dto);
        ApiResponse<Object> rs = ApiResponse.builder().status(true).message("Register user success").data(employees).build();
        return ResponseEntity.ok(rs);

    }
}
