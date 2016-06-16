  
INSERT INTO users(username,password,enabled)
VALUES ('ida','123456', true);
INSERT INTO users(username,password,enabled)
VALUES ('guff','123456', true);

INSERT INTO users(username,password,enabled)
VALUES ('register','123456', true);

INSERT INTO user_roles (username, role)
VALUES ('ida', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('ida', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('guff', 'ROLE_USER');

INSERT INTO user_roles (username, role)
VALUES ('register', 'ROLE_REGISTER');