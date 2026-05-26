CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_role_name UNIQUE (role_name)
);