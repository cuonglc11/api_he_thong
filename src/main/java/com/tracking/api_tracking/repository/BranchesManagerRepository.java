package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.BranchManagers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BranchesManagerRepository extends JpaRepository<BranchManagers, Long> {
    boolean existsByBranches_IdAndEndDateGreaterThanEqual(Long branchId, LocalDate date);}
