package com.example.userrole.dto;

import lombok.Data;

@Data
public class EndpointDto {
    private String path;
    private String httpMethod;
    private String description;
}
