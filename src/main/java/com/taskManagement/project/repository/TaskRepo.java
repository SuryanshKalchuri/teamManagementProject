package com.taskManagement.project.repository;

import com.taskManagement.project.model.Task;
import com.taskManagement.project.model.Projects;
import com.taskManagement.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    Page<Task> findByAssignedTo(User assignedUser,Task.ETaskStatus status,Pageable pageable);

    // 2. Find tasks by project (with pagination)
    Page<Task> findByProject(Projects project, Pageable pageable);

    // 3. Find tasks by assigned user and status (with pagination)
    Page<Task> findByAssignedToAndStatus(User assignedUser, Task.ETaskStatus status, Pageable pageable);

    Page<Task> findByAssignedTo(User user, Pageable pageable);

    // 4. Find tasks by project and status (with pagination)
    Page<Task> findByProjectAndStatus(Projects project, Task.ETaskStatus status, Pageable pageable);

    // 5. Find tasks with due date before or on a specific date (with pagination)
    Page<Task> findByDueDateLessThanEqual(LocalDate date, Pageable pageable);

    // 6. Get all tasks with pagination
    public Page<Task> findAll(Pageable pageable);

    // 7. Find tasks by status
    List<Task> findByStatus(Task.ETaskStatus status);

    // 8. Count tasks by status
    long countByStatus(Task.ETaskStatus status);

    // 9. Count tasks due before or on a specific date
    long countByDueDateLessThanEqual(LocalDate date);

    // 10. Find all tasks ordered by creation date (for recent tasks)
    List<Task> findAllByOrderByCreatedAtDesc();
}
