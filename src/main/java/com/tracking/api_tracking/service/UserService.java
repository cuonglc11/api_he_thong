package com.tracking.api_tracking.service;

import com.tracking.api_tracking.dto.AssignBrancheDto;
import com.tracking.api_tracking.dto.ProvisionManagerDto;
import com.tracking.api_tracking.models.*;
import com.tracking.api_tracking.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BranchesManagerRepository branchesManagerRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Transactional
    public Employees addEmployees(ProvisionManagerDto dto)
    {
       validate(dto);
        Users users = new Users();
        users.setUserName(dto.getUserName());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setIsActive(1);
        Users newUser = userRepository.save(users);
        if(newUser.getId() == null) {
            throw new IllegalArgumentException("save user reject");
        }
        Employees employees = new Employees();
        employees.setUsers(newUser);
        BeanUtils.copyProperties(dto , employees);
        employees.setStatus(1);
        return employeeRepository.save(employees);
    }
    public Employees roleEmployee(Long id  ,  List<String> roles) {
        if(roles.isEmpty()) {
            throw new IllegalArgumentException("Role not cannot be left blank");

        }
        Employees employees = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employess not found"));
        List<Roles> rolesList = roleRepository.findByCodeIn(roles);
        if(rolesList.isEmpty()) {
            throw new IllegalArgumentException("Error: Invalid or non-existent permission in the system");
        }
        Users users = userRepository.findById(employees.getId()).orElseThrow(()-> new IllegalArgumentException("user not fount"));
        users.setRoles(rolesList);
        userRepository.save(users);
        return employees;

    }
    public Employees detailEmployee(Long id)
    {
        Employees employees = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employess not found"));
        return employees;
    }
    public BranchManagers assignManagerStrict(Long id  , AssignBrancheDto dto)
    {
        Branches branches = branchRepository.findById(dto.getBranchId()).orElseThrow(()
        -> new IllegalArgumentException("Branches not found")
        );
        Employees employees = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("emloyees not found"));
        if(branchesManagerRepository.existsByBranchIdAndEndDateIsNull(dto.getBranchId()))
        {
            throw  new IllegalArgumentException("Operation failed: This branch already has a manager in charge.");

        }
        BranchManagers branchManagers = new BranchManagers();
        branchManagers.setBranches(branches);
        branchManagers.setEmployees(employees);
        BeanUtils.copyProperties(dto,branchManagers ,"branchId");
        BranchManagers newSave = branchesManagerRepository.save(branchManagers);
        if(newSave == null) {
            throw new IllegalArgumentException("Save false");
        }
        Users users = employees.getUsers();
        List<Roles> roles = roleRepository.findByCodeIn(Arrays.asList("MANAGER"));
        users.setRoles(roles);
        userRepository.save(users);
        return newSave ;
    }

    private void validate(ProvisionManagerDto dto)
    {
        if(employeeRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Error: The employee code already exists!");
        }
        if(employeeRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Error: The email already exists!");
        }
        if(employeeRepository.existsByPhone(dto.getPhone())) {
            throw new IllegalArgumentException("Error: The phone already exists!");
        }
        if(userRepository.existsByUserName(dto.getUserName())) {
            throw new IllegalArgumentException("Error: The username already exists!");
        }
    }
}
