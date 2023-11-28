CREATE TABLE USUARIOS(
    
ID int,
CRIADO_POR varchar(255),
DATA_CRIACAO date,
DATA_MODIFICACAO date,
MODIFICADO_POR varchar(255),
PASSWORD varchar(255),
ROLE varchar(255),
USERNAME varchar(255) 

);
INSERT INTO USUARIOS (id, username, password, role) VALUES (100, 'xas@gmail.com', '123456', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (101, 'mas@gmail.com', '1234567', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (102, 'zas@gmail.com', '1234568', 'ROLE_ADMIN');
INSERT INTO USUARIOS (id, username, password, role) VALUES (103, 'kas@gmail.com', '1234569', 'ROLE_CLIENTE');
INSERT INTO USUARIOS (id, username, password, role) VALUES (104, 'las@gmail.com', '12345610', 'ROLE_CLIENTE');