CREATE TABLE USERS (id number, login varchar(20), password varchar(20), role varchar(20));

INSERT INTO USERS values (1, 'user', 'user', 'USER');
INSERT INTO USERS values (2, 'admin', 'admin', 'ADMIN');

commit;

CREATE TABLE PROJECTS (id number, name varchar(40), parent boolean, child boolean);

CREATE TABLE TASKS (id number, text text, type varchar(20), status varchar(20));

create table HIERARCHY_PROJECTS(id number, id_parent number, id_child number);

create table HIERARCHY_TASKS(id number, id_project number, id_task number);

create table TASKS_TO_USERS(id number, id_task number, id_user number);

INSERT INTO PROJECTS values (1000, 'project1', false, true );
INSERT INTO PROJECTS values (1100,'subproject1',true, true);
INSERT INTO PROJECTS values (1200, 'subproject2', true, false);
INSERT INTO PROJECTS values (1110, 'subSubproject1', true, false);
INSERT INTO HIERARCHY_PROJECTS values (1, 1000, 1100);
INSERT INTO HIERARCHY_PROJECTS values (2, 1000, 1200);
INSERT INTO HIERARCHY_PROJECTS values (3, 1100, 1110);
INSERT INTO TASKS values (1000, 'task1', 'ENGINEER', 'NEW');
INSERT INTO TASKS values (2000, 'task2', 'ENGINEER', 'NEW');
INSERT INTO TASKS values (2000, 'task3', 'ENGINEER', 'NEW');
INSERT INTO HIERARCHY_TASKS values (1, 1000, 1000);
INSERT INTO HIERARCHY_TASKS values (1, 1200, 2000);
INSERT INTO HIERARCHY_TASKS values (1, 1110, 3000);
commit;

