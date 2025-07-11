package com.taskManagement.project.service;

import com.taskManagement.project.model.Projects;
import com.taskManagement.project.model.User;
import com.taskManagement.project.repository.ProjectRepo;
import com.taskManagement.project.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    public Projects createProject(Projects project, Long managerId) {
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty.");
        }

        User manager = userRepo.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Manager with ID " + managerId + " not found."));

        project.setProjectManager(manager);
        return projectRepo.save(project);
    }

    public Page<Projects> getAllProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }

    public Page<Projects> getProjectsByManager(Long managerId, Pageable pageable) {
        User manager = userRepo.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("Manager with ID " + managerId + " not found."));
        return projectRepo.findByProjectManager(manager, pageable);
    }

    public Projects updateProject(Long projectId, Projects updatedProject, Long newManagerId) {
        Projects existing = projectRepo.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found."));

        if (updatedProject.getName() != null && !updatedProject.getName().trim().isEmpty()) {
            existing.setName(updatedProject.getName());
        }

        if (updatedProject.getDescription() != null) {
            existing.setDescription(updatedProject.getDescription());
        }

        if (updatedProject.getStartDate() != null) {
            existing.setStartDate(updatedProject.getStartDate());
        }

        if (updatedProject.getEndDate() != null) {
            existing.setEndDate(updatedProject.getEndDate());
        }

        if (newManagerId != null) {
            User newManager = userRepo.findById(newManagerId)
                    .orElseThrow(() -> new IllegalArgumentException("Manager with ID " + newManagerId + " not found."));
            existing.setProjectManager(newManager);
        }

        return projectRepo.save(existing);
    }

    public void deleteProject(Long projectId) {
        Projects project = projectRepo.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found."));
        projectRepo.delete(project);
    }
}