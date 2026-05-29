package com.example.userrole.service;

import com.example.userrole.dto.AssignResult;
import com.example.userrole.dto.EndpointDto;
import com.example.userrole.entity.Endpoint;
import com.example.userrole.entity.Role;
import com.example.userrole.entity.RoleEndpointMapping;
import com.example.userrole.repository.EndpointRepository;
import com.example.userrole.repository.RoleEndpointMappingRepository;
import com.example.userrole.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleEndpointService {

    private final RoleRepository roleRepo;
    private final EndpointRepository endpointRepo;
    private final RoleEndpointMappingRepository mappingRepo;

    // Assign a list of endpoints to a role
    @Transactional
    public List<AssignResult> assignEndpointsToRole(String roleName, List<EndpointDto> endpointDtos) {
        List<AssignResult> results = new ArrayList<>();

        Optional<Role> roleOpt = roleRepo.findByRoleName(roleName.trim().toUpperCase());
        if (roleOpt.isEmpty()) {
            // role not found — report for every endpoint requested
            endpointDtos.forEach(dto ->
                    results.add(new AssignResult(roleName, dto.getPath(), "ROLE_NOT_FOUND")));
            return results;
        }
        Role role = roleOpt.get();

        for (EndpointDto dto : endpointDtos) {
            String path = dto.getPath().trim();
            String method = dto.getHttpMethod().trim().toUpperCase();

            Optional<Endpoint> endpointOpt = endpointRepo.findByPathAndHttpMethod(path, method);
            if (endpointOpt.isEmpty()) {
                results.add(new AssignResult(roleName, path + " [" + method + "]", "ENDPOINT_NOT_FOUND"));
                continue;
            }
            Endpoint endpoint = endpointOpt.get();

            if (mappingRepo.existsByRoleIdAndEndpointId(role.getId(), endpoint.getId())) {
                results.add(new AssignResult(roleName, path + " [" + method + "]", "ALREADY_EXISTS"));
            } else {
                mappingRepo.save(new RoleEndpointMapping(role, endpoint));
                results.add(new AssignResult(roleName, path + " [" + method + "]", "ASSIGNED"));
            }
        }
        return results;
    }

    // Get all endpoints accessible by a role
    public List<String> getEndpointsForRole(String roleName) {
        Role role = roleRepo.findByRoleName(roleName.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
        return mappingRepo.findByRoleId(role.getId())
                .stream()
                .map(m -> m.getEndpoint().getHttpMethod() + " " + m.getEndpoint().getPath())
                .toList();
    }

    // Get all roles that can access a given endpoint
    public List<String> getRolesForEndpoint(String path, String method) {
        Endpoint endpoint = endpointRepo
                .findByPathAndHttpMethod(path.trim(), method.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Endpoint not found: " + method + " " + path));
        return mappingRepo.findByEndpointId(endpoint.getId())
                .stream()
                .map(m -> m.getRole().getRoleName())
                .toList();
    }

    public List<Endpoint> getAllEndpoints() {
        return endpointRepo.findAll();
    }
}