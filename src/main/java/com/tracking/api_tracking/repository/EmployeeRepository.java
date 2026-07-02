package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees , Long> {
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
