CREATE TABLE user_role_mapping (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_mapping_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_mapping_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);