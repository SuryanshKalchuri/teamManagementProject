package com.taskManagement.project.service;

import com.taskManagement.project.model.Projects;
import com.taskManagement.project.model.Task;
import com.taskManagement.project.model.User;
import com.taskManagement.project.repository.ProjectRepo;
import com.taskManagement.project.repository.TaskRepo;
import com.taskManagement.project.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final ProjectRepo projectRepo;

    public Task createTask(Task task, Long projectId,  Long assignedTo) {

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        if (task.getDueDate() == null) {
            throw new IllegalArgumentException("Due date must not be null.");
        }

        if (task.getDueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }

        Projects project = projectRepo.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project with ID " + projectId + " not found."));

        if (task.getAssignedTo() != null) {
            User assignedUser = userRepo.findById(assignedTo)
                    .orElseThrow(() -> new IllegalArgumentException("User with ID " + assignedTo + " not found."));
            task.setAssignedTo(assignedUser);
        } else {
            task.setAssignedTo(null);
        }

        if (task.getStatus() == null) {
            task.setStatus(Task.ETaskStatus.TO_DO);
        }

        if (task.getPriority() == null) {
            task.setPriority(Task.ETaskPriority.MEDIUM);
        }

        task.setProject(project);
        return taskRepo.save(task);

    }
}