package com.tracking.api_tracking.controllers;

import com.tracking.api_tracking.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/test")
    public ResponseEntity<ApiResponse<Object>> test() {
        return ResponseEntity.ok(ApiResponse.builder().message("heeee").build());
    }
}
