package com.example.userrole.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleEndpointAssignRequest {
    private String roleName;
    private List<EndpointDto> endpoints;
}

