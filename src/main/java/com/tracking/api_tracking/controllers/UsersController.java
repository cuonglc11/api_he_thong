package com.tracking.api_tracking.controllers;

import com.tracking.api_tracking.dto.*;
import com.tracking.api_tracking.models.Employees;
import com.tracking.api_tracking.response.ApiResponse;
import com.tracking.api_tracking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UsersController {
    @Autowired
    private UserService userService;
    @PostMapping("/managers")
    public ResponseEntity<ApiResponse<Object>> managersPost(@Valid @RequestBody ProvisionManagerDto dto)
    {
        ApiResponse<Object> rs = ApiResponse.builder().status(true).data(userService.addEmployees(dto)).message("Employess save success").build();
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/employee")
    public ResponseEntity<Page<Employees>> getAll(   @RequestParam(required = false) String keyword,
                                                     @RequestParam(defaultValue = "0") int page ,
                                                     @RequestParam(defaultValue = "10") int size)
    {
        return  ResponseEntity.ok(userService.getList(keyword , PageRequest.of(page , size)));
    }
    @PostMapping("/{id}/roles")
    public ResponseEntity<ApiResponse<Object>> updateRoles(@PathVariable("id") Long id  , @RequestBody List<String> roles) {
        ApiResponse<Object> rs = ApiResponse.builder().status(true).data(userService.roleEmployee(id , roles)).message("Employess save roles success").build();
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> detailEmployee(@PathVariable("id") Long id)
    {
        ApiResponse<Object> rs  = ApiResponse.builder().status(true).data(userService.detailEmployee(id)).build();
        return ResponseEntity.ok(rs);
    }
    @PostMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<Object>> assign(@PathVariable("id") Long id, @RequestBody AssignBrancheDto dto )
    {
        ApiResponse<Object> apiResponse = ApiResponse.builder().status(true).data(userService.assignManagerStrict(id,dto)).message("assign success").build();
        return ResponseEntity.ok(apiResponse);
    }

}
