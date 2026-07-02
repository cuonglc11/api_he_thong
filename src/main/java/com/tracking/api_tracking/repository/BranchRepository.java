package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.Branches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branches , Long> {
    boolean existsByCode(String code);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByName(String name);
}
