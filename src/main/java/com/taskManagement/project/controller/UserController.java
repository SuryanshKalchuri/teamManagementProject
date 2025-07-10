package com.taskManagement.project.controller;

import com.taskManagement.project.model.User;
import com.taskManagement.project.repository.UserRepo;
import com.taskManagement.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = {
//        "http://localhost:8080",
//        "http://127.0.0.1:5500"
//})
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<?> updateUserRoles(@PathVariable Long id, @RequestBody Map<String, Set<String>> rolesMap){
        Set<String> roleNames = rolesMap.get("roles");
        if (roleNames == null || roleNames.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: field is required.");
        }

        try {
            User updatedUser = userService.updateUserRoles(id, roleNames);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROJECT_MANAGER')")
    public ResponseEntity<?> deleteUseer(@PathVariable Long id){
        Optional<User> userOptional = userService.findUserById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with ID " + id + " not found.");
        }

        userService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted successfully.");
    }
}
