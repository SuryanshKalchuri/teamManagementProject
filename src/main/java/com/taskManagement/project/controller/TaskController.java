package com.taskManagement.project.controller;

import com.taskManagement.project.model.Task;
import com.taskManagement.project.service.TaskService;
import com.taskManagement.project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:5500"})
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task,
                                        @RequestParam Long projectId,
                                        @RequestParam(required = false) Long assignedTo) {
        try {
            Task createdTask = taskService.createTask(task, projectId, assignedTo);
            return ResponseEntity.ok(createdTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskService.getAllTasks(pageable);
    }

    @GetMapping("/assigned/{userId}")
    public Page<Task> getTasksByAssignedUser(@PathVariable Long userId, Pageable pageable) {
        return taskService.getTasksByAssignedUser(userId, pageable);
    }

    @GetMapping("/project/{projectId}/status")
    public Page<Task> getTasksByProjectAndStatus(@PathVariable Long projectId,
                                                 @RequestParam Task.ETaskStatus status,
                                                 Pageable pageable) {
        return taskService.getTasksByProjectAndStatus(projectId, status, pageable);
    }

    @GetMapping("/status")
    public Page<Task> getTasksByStatus(@RequestParam Task.ETaskStatus status, Pageable pageable) {
        return taskService.getTasksByStatus(status, pageable);
    }

    @GetMapping("/project/{projectId}")
    public Page<Task> getTasksByProject(@PathVariable Long projectId, Pageable pageable) {
        return taskService.getTasksByProject(projectId, pageable);
    }

    @GetMapping("/assigned/{userId}/status")
    public Page<Task> getTasksByAssignedUserAndStatus(@PathVariable Long userId,
                                                      @RequestParam Task.ETaskStatus status,
                                                      Pageable pageable) {
        return taskService.getTasksByAssignedUserAndStatus(userId, status, pageable);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok("Task with ID " + taskId + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId,
                                        @RequestBody Task task,
                                        @RequestParam(required = false) Long assignedTo,
                                        @RequestParam(required = false) Long projectId) {
        try {
            Task updated = taskService.updateTask(taskId, task, assignedTo, projectId);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId,
                                              @RequestParam Task.ETaskStatus status) {
        try {
            Task updated = taskService.updateTaskStatus(taskId, status);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}