CREATE TABLE USERS (id number, login varchar(20), password varchar(20), role varchar(20));

INSERT INTO USERS values (1, 'user', 'user', 'USER');
INSERT INTO USERS values (2, 'admin', 'admin', 'ADMIN');

commit;

CREATE TABLE PROJECTS (id number, name varchar(40), parent boolean, child boolean);

CREATE TABLE TASKS (id number, text text, type varchar(20), status varchar(20));

create table HIERARCHY_PROJECTS(id number, id_parent number, id_child number);

create table HIERARCHY_TASKS(id number, id_project number, id_task number);

create table TASKS_TO_USERS(id number, id_task number, id_user number);
