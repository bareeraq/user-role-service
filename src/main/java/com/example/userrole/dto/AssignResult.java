package com.example.userrole.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AssignResult {
    private String username;
    private String roleName;
    private String status;
}


// ASSIGNED | ALREADY_EXISTS | USER_NOT_FOUND | ROLE_NOT_FOUND (status)