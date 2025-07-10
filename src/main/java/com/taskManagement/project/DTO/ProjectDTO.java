package com.taskManagement.project.DTO;

import com.taskManagement.project.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User projectManager;
    private LocalDateTime createdAt;
}
