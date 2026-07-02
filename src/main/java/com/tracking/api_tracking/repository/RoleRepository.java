package com.tracking.api_tracking.repository;

import com.tracking.api_tracking.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Roles , Long> {
    List<Roles> findByCodeIn(Iterable<String> codes);
}
