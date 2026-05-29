package com.example.userrole.repository;

import com.example.userrole.entity.Endpoint;
import com.example.userrole.entity.RoleEndpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    Optional<Endpoint> findByPathAndHttpMethod(String path, String httpMethod);
}

