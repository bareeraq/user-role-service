package com.example.userrole.controller;

import com.example.userrole.dto.*;
import com.example.userrole.entity.*;
import com.example.userrole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService service;

    @PostMapping("/users")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<String> usernames) {
        return ResponseEntity.ok(service.createUsers(usernames));
    }

    @PostMapping("/roles")
    public ResponseEntity<List<Role>> createRoles(@RequestBody List<String> roleNames) {
        return ResponseEntity.ok(service.createRoles(roleNames));
    }

    @PostMapping("/assign")
    public ResponseEntity<List<AssignResult>> assign(@RequestBody AssignRequest req) {
        return ResponseEntity.ok(
                service.assignRolesToUsers(req.getUsernames(), req.getRoleNames()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(service.getAllRoles());
    }

    @GetMapping("/users/{username}/roles")
    public ResponseEntity<List<String>> getRolesForUser(@PathVariable String username) {
        return ResponseEntity.ok(service.getRolesForUser(username));
    }

    @GetMapping("/roles/{roleName}/users")
    public ResponseEntity<List<String>> getUsersForRole(@PathVariable String roleName) {
        return ResponseEntity.ok(service.getUsersForRole(roleName));
    }
}