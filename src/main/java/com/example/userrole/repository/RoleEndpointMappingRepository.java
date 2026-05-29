package com.example.userrole.repository;

import com.example.userrole.entity.RoleEndpointMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleEndpointMappingRepository
        extends JpaRepository<RoleEndpointMapping, RoleEndpointMapping.RoleEndpointId> {

    @Query("SELECT m FROM RoleEndpointMapping m JOIN FETCH m.endpoint WHERE m.role.id = :roleId")
    List<RoleEndpointMapping> findByRoleId(@Param("roleId") Long roleId);

    @Query("SELECT m FROM RoleEndpointMapping m JOIN FETCH m.role WHERE m.endpoint.id = :endpointId")
    List<RoleEndpointMapping> findByEndpointId(@Param("endpointId") Long endpointId);

    boolean existsByRoleIdAndEndpointId(Long roleId, Long endpointId);
}
