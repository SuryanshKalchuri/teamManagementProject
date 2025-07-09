package com.taskManagement.project.repository;

import com.taskManagement.project.model.Projects;
import com.taskManagement.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepo extends JpaRepository<Projects, Long> {

    Page<Projects> findByProjectManager(User projectManager, Pageable pageable);

    Page<Projects> findAll(Pageable pageable);


    long countActiveProjects(LocalDate today);
    List<Projects> findAllByOrderByCreatedAtDesc();
}
