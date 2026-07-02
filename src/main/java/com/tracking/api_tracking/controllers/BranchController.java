package com.tracking.api_tracking.controllers;

import com.tracking.api_tracking.dto.*;
import com.tracking.api_tracking.response.ApiResponse;
import com.tracking.api_tracking.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Object>> addBranch(@Valid @RequestBody BranchDto dto)
    {
       ApiResponse<Object> apiResponse = ApiResponse.builder().status(true).data(branchService.addBranch(dto)).message("Add branch success").build();
       return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> update(@PathVariable Long id , @RequestBody BranchUpdateDto dto)
    {
        ApiResponse<Object> rs = ApiResponse.builder().status(true).data(branchService.updateBranch(id,dto)).message("Update Branch Success ").build();
        return ResponseEntity.ok(rs);
    }

}
