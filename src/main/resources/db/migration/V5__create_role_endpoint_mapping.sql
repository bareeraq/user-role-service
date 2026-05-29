CREATE TABLE role_endpoint_mapping (
    role_id BIGINT NOT NULL,
    endpoint_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, endpoint_id),
    CONSTRAINT fk_rem_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_rem_endpoint FOREIGN KEY (endpoint_id) REFERENCES endpoints(id) ON DELETE CASCADE
);