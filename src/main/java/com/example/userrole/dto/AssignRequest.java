package com.example.userrole.dto;

import lombok.Data;
import java.util.List;

@Data
public class AssignRequest {
    private List<String> usernames;
    private List<String> roleNames;
}