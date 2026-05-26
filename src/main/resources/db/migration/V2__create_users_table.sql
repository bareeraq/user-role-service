CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150),
    PRIMARY KEY (id),
    CONSTRAINT uk_username UNIQUE (username)
);