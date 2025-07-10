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

    Page<Task> findByProject(Projects project, Pageable pageable);

    Page<Task> findByAssignedToAndStatus(User assignedUser, Task.ETaskStatus status, Pageable pageable);

    Page<Task> findByAssignedTo(User user, Pageable pageable);

    Page<Task> findByProjectAndStatus(Projects project, Task.ETaskStatus status, Pageable pageable);


    Page<Task> findByDueDateLessThanEqual(LocalDate date, Pageable pageable);

    public Page<Task> findAll(Pageable pageable);

    Page<Task> findByStatus(Task.ETaskStatus status, Pageable pageable);

    long countByStatus(Task.ETaskStatus status);

    long countByDueDateLessThanEqual(LocalDate date);

    List<Task> findAllByOrderByCreatedAtDesc();
}
