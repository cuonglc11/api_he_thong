package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employees , Long> {
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query("SELECT e FROM Employees e WHERE " +
            "LOWER(e.code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.phone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Employees> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
