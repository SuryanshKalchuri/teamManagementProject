package com.taskManagement.project.repository;

import com.taskManagement.project.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(Roles.ERole name);
}
