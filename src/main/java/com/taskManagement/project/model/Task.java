package com.taskManagement.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition ="TEXT",nullable = false)
    private String description;

    public enum ETaskStatus {
        TO_DO,
        IN_PROGRESS,
        DONE,
        BLOCKED
    }
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ETaskStatus status = ETaskStatus.TO_DO;

    public enum ETaskPriority {
        LOW,
        MEDIUM,
        HIGH,
        URGENT
    }
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ETaskPriority priority = ETaskPriority.MEDIUM;

    @Column(name = "due_date", updatable = false)
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Projects project;


    @ManyToOne
    @JoinColumn(name = "assigned_to_id", nullable = false)
    private User assignedTo;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
