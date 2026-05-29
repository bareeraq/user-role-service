package com.example.userrole.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endpoints")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String path;

    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod;

    @Column(length = 255)
    private String description;
}
