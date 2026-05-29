package com.example.userrole.controller;

import com.example.userrole.dto.AssignResult;
import com.example.userrole.dto.EndpointDto;
import com.example.userrole.dto.RoleEndpointAssignRequest;
import com.example.userrole.entity.Endpoint;
import com.example.userrole.service.RoleEndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleEndpointController {

    private final RoleEndpointService service;

    // Get all registered endpoints
    @GetMapping("/endpoints")
    public ResponseEntity<List<Endpoint>> getAllEndpoints() {
        return ResponseEntity.ok(service.getAllEndpoints());
    }

    // Assign endpoints to a role
    @PostMapping("/roles/assign-endpoints")
    public ResponseEntity<List<AssignResult>> assignEndpoints(
            @RequestBody RoleEndpointAssignRequest req) {
        return ResponseEntity.ok(
                service.assignEndpointsToRole(req.getRoleName(), req.getEndpoints()));
    }

    // Get all endpoints a role can access
    @GetMapping("/roles/{roleName}/endpoints")
    public ResponseEntity<List<String>> getEndpointsForRole(@PathVariable String roleName) {
        return ResponseEntity.ok(service.getEndpointsForRole(roleName));
    }

    // Get all roles that can access a specific endpoint
    @GetMapping("/endpoints/roles")
    public ResponseEntity<List<String>> getRolesForEndpoint(
            @RequestParam String path,
            @RequestParam String method) {
        return ResponseEntity.ok(service.getRolesForEndpoint(path, method));
    }
}