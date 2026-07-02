package com.tracking.api_tracking.service;

import com.tracking.api_tracking.dto.*;
import com.tracking.api_tracking.models.Branches;
import com.tracking.api_tracking.repository.BranchRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    public Branches addBranch(BranchDto dto)
    {
        Branches branches = new Branches();
         validate(dto);
        BeanUtils.copyProperties(dto , branches);
        return branchRepository.save(branches);
    }
    public Branches updateBranch(Long id , BranchUpdateDto dto) {
        Branches branches = branchRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("Branch not found")
        );
        validateUpdate(dto , branches);
       BeanUtils.copyProperties(dto , branches , "id");
        return branchRepository.save(branches);

    }
    private void validate(BranchDto dto)
    {
        if(branchRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email branches already exist within the business.");
        }
        if(branchRepository.existsByCode(dto.getCode())) {
            throw  new IllegalArgumentException("Code branches already exist within the business.");
        }
        if(branchRepository.existsByPhone(dto.getPhone())) {
            throw  new IllegalArgumentException("Phone branches already exist within the business.");
        }
        if(branchRepository.existsByName(dto.getName())) {
            throw  new IllegalArgumentException("Name branches already exist within the business.");
        }
    }
    private void validateUpdate(BranchUpdateDto dto , Branches  branches)
    {
        if(dto.getCode() != null || !dto.getCode().equals(branches.getCode())){
            if(branchRepository.existsByCode(dto.getCode())) {
                throw  new IllegalArgumentException("Code branches already exist within the business.");
            }
        }
        if(dto.getEmail() != null || !dto.getEmail().equals(branches.getEmail())){
            if(branchRepository.existsByEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Email branches already exist within the business.");
            }
        }
        if(dto.getPhone() != null || !dto.getPhone().equals(branches.getPhone())){
            if(branchRepository.existsByPhone(dto.getPhone())) {
                throw  new IllegalArgumentException("Phone branches already exist within the business.");
            }
        }
        if(dto.getName() != null || !dto.getName().equals(branches.getName())){
            if(branchRepository.existsByName(dto.getName())) {
                throw  new IllegalArgumentException("Name branches already exist within the business.");
            }
        }

    }
}
