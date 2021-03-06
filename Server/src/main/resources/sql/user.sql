-- SAMPLE USER
INSERT INTO SHINE_USER(ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUTATION)
VALUES (-1, true, 'smith', 'smith@domain.com', '$2a$10$wgsBKRLSrt0JdEM1gRK0xuhwSflI3O/B9S6uiPs2z7VIsjZnqKz4q',
        CURRENT_TIMESTAMP(), 0);

-- ANONYMOUS USER
INSERT INTO SHINE_USER(ID, FLAG_STATUS, LOGIN, EMAIL, PASSWORD, REGISTER_TIME, REPUTATION)
VALUES (-2, true, 'anonymous_user', 'anonymous@domain.com', 'not_use', CURRENT_TIMESTAMP(), 0);


-- DEFAULT ROLES
INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-1, 'anonymous_role', 'anonymous role', true, 30);

INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-2, 'registered_user_role', 'registered user role', false, 0);

INSERT INTO SHINE_ROLE(ID, NAME, DESCRIPTION, DEFAULT_ROLE, ROLE_TYPE)
VALUES (-3, 'administration', 'administration role', false, 10);


-- USER <-------> ROLES
INSERT INTO SHINE_USER_ROLE_XREF(ID, SHINE_USER_ID, SHINE_ROLE_ID)
VALUES (-1, -2, -1);

INSERT INTO SHINE_USER_ROLE_XREF(ID, SHINE_USER_ID, SHINE_ROLE_ID)
VALUES (-2, -1, -2);

-- PERMISSION
INSERT INTO SHINE_PERMISSION(ID, PERMISSION_TYPE, TARGET, VALUE, ADDITIONAL_CONDITION, SHINE_ROLE)
VALUES (-1, 40, 'specific_vote_question', 1, '0', -2);




