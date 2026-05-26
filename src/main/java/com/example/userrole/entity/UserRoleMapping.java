package com.example.userrole.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;

@Entity
@Table(name = "user_role_mapping")
@Getter @Setter @NoArgsConstructor
public class UserRoleMapping {

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRoleId implements Serializable {
        private Long userId;
        private Long roleId;

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(!(o instanceof UserRoleId that)) return false;
            return Objects.equals(userId, that.userId) &&
                    Objects.equals(roleId, that.roleId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, roleId);
        }
    }

    @EmbeddedId
    private UserRoleId id = new UserRoleId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserRoleMapping(User user, Role role){
        this.user = user;
        this.role = role;
        this.id = new UserRoleId(user.getId(), role.getId());
    }
}