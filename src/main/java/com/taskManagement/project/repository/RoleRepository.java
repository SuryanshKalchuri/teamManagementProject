package com.taskManagement.project.repository;

import com.taskManagement.project.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleRepository, Long> {
    Optional<RoleRepository> findByName(Roles.ERole name);
}
