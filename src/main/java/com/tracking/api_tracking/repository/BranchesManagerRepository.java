package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.BranchManagers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchesManagerRepository extends JpaRepository<BranchManagers, Long> {
    boolean existsByBranchIdAndEndDateIsNull(Long branchId);
}
