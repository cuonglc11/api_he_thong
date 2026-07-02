package com.tracking.api_tracking.service;

import com.tracking.api_tracking.config.JwtProvider;
import com.tracking.api_tracking.dto.*;
import com.tracking.api_tracking.models.*;
import com.tracking.api_tracking.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public String login(LoginDto dto)
    {
        Users users = userRepository.findByUserName(dto.getUsername()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        if(!passwordEncoder.matches(dto.getPassword(),  users.getPassword())) {
            throw  new IllegalArgumentException("pass false");
        }
        List<String> rolesCode =  users.getRoles().stream().map(role -> role.getCode()).collect(Collectors.toList());
        return jwtProvider.generateToken(users.getUserName(), rolesCode , users.getId());
    }
    @Transactional
    public  Employees registerUser(RegisterDto dto)
    {

        Branches branches = branchRepository.findById(dto.getBranchId()).orElseThrow(()-> new IllegalArgumentException(" Error:  branch not found"));
        List<Roles> roles = roleRepository.findByCodeIn(dto.getRoles());
        validate(dto , roles);
        Users users = new Users();
        users.setUserName(dto.getUserName());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setRoles(roles);
        users.setIsActive(1);
        Users newUser = userRepository.save(users);
        if(newUser.getId() == null) {
            throw new IllegalArgumentException("False to user save");
        }
        Employees employees = new Employees();
        BeanUtils.copyProperties(dto , employees);
        employees.setUsers(newUser);
        employees.setBranches(branches);
        employees.setStatus(1);
        return employeeRepository.save(employees);



    }
    private void validate(RegisterDto dto , List<Roles> roles)
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
        if(roles.isEmpty()) {
            throw new IllegalArgumentException("Error: Invalid or non-existent permission in the system");
        }
    }
}
