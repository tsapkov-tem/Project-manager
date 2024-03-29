CREATE TABLE USERS (id number PRIMARY KEY AUTO_INCREMENT, login varchar(20) NOT NULL, password varchar(100) NOT NULL, role varchar(20) NOT NULL);

CREATE TABLE PROJECTS (id number PRIMARY KEY AUTO_INCREMENT, name varchar(40) NOT NULL, parent boolean NOT NULL, child boolean NOT NULL);

CREATE TABLE TASKS (id number PRIMARY KEY AUTO_INCREMENT, name varchar(20) NOT NULL, date_change varchar(30) NOT NULL, date_create varchar(30) NOT NULL, text text NOT NULL, type varchar(20) NOT NULL, status varchar(20) NOT NULL);

create table HIERARCHY_PROJECTS(id number PRIMARY KEY AUTO_INCREMENT, id_parent number, id_child number);

create table HIERARCHY_TASKS(id number PRIMARY KEY AUTO_INCREMENT, id_project number, id_task number);

create table TASKS_TO_USERS(id number PRIMARY KEY AUTO_INCREMENT, id_task number, id_user number);



INSERT INTO USERS values (0, 'admin', 'admin', 'ADMIN');
INSERT INTO USERS values (1, 'user', 'user', 'USER');


INSERT INTO USERS values (0, 'admin', 'admin', 'ADMIN');
INSERT INTO USERS values (1, 'user', 'user', 'USER');
INSERT INTO PROJECTS (name, parent, child) values ('project1', false, true );
INSERT INTO PROJECTS (name, parent, child) values ('subproject1',true, true);
INSERT INTO PROJECTS (name, parent, child) values ('subproject2', true, false);
INSERT INTO PROJECTS (name, parent, child) values ('subSubproject1', true, false);
INSERT INTO HIERARCHY_PROJECTS (id_parent, id_child) values (1000, 1100);
INSERT INTO HIERARCHY_PROJECTS (id_parent, id_child) values (1000, 1200);
INSERT INTO HIERARCHY_PROJECTS (id_parent, id_child) values (1100, 1110);
INSERT INTO TASKS (name, date_change, date_create, text, type, status) values ('name', 'task1', 'date','date' , 'ENGINEER', 'NEW');
INSERT INTO TASKS (name, date_change, date_create, text, type, status) values ('name', 'task2', 'date','date' ,'ENGINEER', 'NEW');
INSERT INTO TASKS (name, date_change, date_create, text, type, status) values ('name', 'task3', 'date','date' ,'ENGINEER', 'NEW');
INSERT INTO HIERARCHY_TASKS (id_project, id_task) values (1000, 1000);
INSERT INTO HIERARCHY_TASKS (id_project, id_task) values (1200, 2000);
INSERT INTO HIERARCHY_TASKS (id_project, id_task) values (1110, 3000);
commit;

