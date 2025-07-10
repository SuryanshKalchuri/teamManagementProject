package com.taskManagement.project.DTO;

import com.taskManagement.project.model.Projects;
import com.taskManagement.project.model.Task;
import com.taskManagement.project.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Projects project;
    private User assignedTo;
    private LocalDateTime createdAt;
}
