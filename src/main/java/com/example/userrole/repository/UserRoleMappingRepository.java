package com.example.userrole.repository;

import com.example.userrole.entity.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRoleMappingRepository
        extends JpaRepository<UserRoleMapping, UserRoleMapping.UserRoleId> {

    @Query("SELECT m FROM UserRoleMapping m JOIN FETCH m.role WHERE m.user.id = :userId")
    List<UserRoleMapping> findByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM UserRoleMapping m JOIN FETCH m.user WHERE m.role.id = :roleId")
    List<UserRoleMapping> findByRoleId(@Param("roleId") Long roleId);

    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}