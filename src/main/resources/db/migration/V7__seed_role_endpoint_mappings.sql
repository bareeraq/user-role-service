INSERT INTO role_endpoint_mapping (role_id, endpoint_id)
SELECT r.id, e.id FROM roles r, endpoints e
WHERE r.role_name = 'ADMIN'
AND e.path IN ('/organization/create', '/organization/update', '/project/create', '/project/update');

INSERT INTO role_endpoint_mapping (role_id, endpoint_id)
SELECT r.id, e.id FROM roles r, endpoints e
WHERE r.role_name = 'MANAGER'
AND e.path IN ('/project/create', '/project/update');

INSERT INTO role_endpoint_mapping (role_id, endpoint_id)
SELECT r.id, e.id FROM roles r, endpoints e
WHERE r.role_name = 'EMPLOYEE'
AND e.path IN ('/employee/getById', '/profile/update');