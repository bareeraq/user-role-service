package com.example.userrole.service;

import com.example.userrole.dto.AssignResult;
import com.example.userrole.entity.*;
import com.example.userrole.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserRoleMappingRepository mappingRepo;

    @Transactional
    public List<User> createUsers(List<String> usernames) {
        List<User> result = new ArrayList<>();
        for (String name : usernames) {
            result.add(
                    userRepo.findByUsername(name.trim())
                            .orElseGet(() -> userRepo.save(new User(name.trim(), null)))
            );
        }
        return result;
    }

    @Transactional
    public List<Role> createRoles(List<String> roleNames) {
        List<Role> result = new ArrayList<>();
        for (String name : roleNames) {
            String normalized = name.trim().toUpperCase();
            result.add(
                    roleRepo.findByRoleName(normalized)
                            .orElseGet(() -> roleRepo.save(new Role(normalized)))
            );
        }
        return result;
    }

    @Transactional
    public List<AssignResult> assignRolesToUsers(List<String> usernames, List<String> roleNames) {
        List<AssignResult> results = new ArrayList<>();

        for (String username : usernames) {
            Optional<User> userOpt = userRepo.findByUsername(username.trim());
            if (userOpt.isEmpty()) {
                roleNames.forEach(r ->
                        results.add(new AssignResult(username, r, "USER_NOT_FOUND")));
                continue;
            }
            User user = userOpt.get();

            for (String roleName : roleNames) {
                String normalized = roleName.trim().toUpperCase();
                Optional<Role> roleOpt = roleRepo.findByRoleName(normalized);
                if (roleOpt.isEmpty()) {
                    results.add(new AssignResult(username, normalized, "ROLE_NOT_FOUND"));
                    continue;
                }
                Role role = roleOpt.get();

                if (mappingRepo.existsByUserIdAndRoleId(user.getId(), role.getId())) {
                    results.add(new AssignResult(username, normalized, "ALREADY_EXISTS"));
                } else {
                    mappingRepo.save(new UserRoleMapping(user, role));
                    results.add(new AssignResult(username, normalized, "ASSIGNED"));
                }
            }
        }
        return results;
    }

    public List<User> getAllUsers() { return userRepo.findAll(); }
    public List<Role> getAllRoles() { return roleRepo.findAll(); }

    public List<String> getRolesForUser(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return mappingRepo.findByUserId(user.getId())
                .stream().map(m -> m.getRole().getRoleName()).toList();
    }

    public List<String> getUsersForRole(String roleName) {
        Role role = roleRepo.findByRoleName(roleName.toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        return mappingRepo.findByRoleId(role.getId())
                .stream().map(m -> m.getUser().getUsername()).toList();
    }
}