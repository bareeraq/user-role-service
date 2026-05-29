CREATE TABLE endpoints (
    id BIGINT NOT NULL AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    http_method VARCHAR(10) NOT NULL,
    description VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT uk_endpoint UNIQUE (path, http_method)
);

INSERT INTO endpoints (path, http_method, description) VALUES
('/organization/create', 'POST', 'Create a new organization'),
('/organization/update', 'PUT',  'Update an organization'),
('/project/create',      'POST', 'Create a new project'),
('/project/update',      'PUT',  'Update a project'),
('/employee/getById',    'GET',  'Get employee by ID'),
('/profile/update',      'PUT',  'Update user profile');