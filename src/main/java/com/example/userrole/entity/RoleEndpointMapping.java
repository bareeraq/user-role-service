package com.example.userrole.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "role_endpoint_mapping")
@Getter @Setter @NoArgsConstructor
public class RoleEndpointMapping {

    @Embeddable
    @NoArgsConstructor @AllArgsConstructor
    public static class RoleEndpointId implements Serializable {
        private Long roleId;
        private Long endpointId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RoleEndpointId that)) return false;
            return Objects.equals(roleId, that.roleId) &&
                    Objects.equals(endpointId, that.endpointId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(roleId, endpointId);
        }
    }

    @EmbeddedId
    private RoleEndpointId id = new RoleEndpointId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("endpointId")
    @JoinColumn(name = "endpoint_id", nullable = false)
    private Endpoint endpoint;

    public RoleEndpointMapping(Role role, Endpoint endpoint) {
        this.role = role;
        this.endpoint = endpoint;
        this.id = new RoleEndpointId(role.getId(), endpoint.getId());
    }
}
