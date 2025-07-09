package com.taskManagement.project.repository;

import com.taskManagement.project.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(Roles.ERole name);
}
