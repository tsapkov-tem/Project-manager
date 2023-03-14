CREATE TABLE USERS (id number PRIMARY KEY AUTO_INCREMENT, login varchar(20) NOT NULL, password varchar(100) NOT NULL, role varchar(20) NOT NULL);

CREATE TABLE PROJECTS (id number PRIMARY KEY AUTO_INCREMENT, name varchar(40) NOT NULL, parent boolean NOT NULL, child boolean NOT NULL);

CREATE TABLE TASKS (id number PRIMARY KEY AUTO_INCREMENT, name varchar(20) NOT NULL, date_change varchar(30) NOT NULL, date_create varchar(30) NOT NULL, text text NOT NULL, type varchar(20) NOT NULL, status varchar(20) NOT NULL);

create table HIERARCHY_PROJECTS(id number PRIMARY KEY AUTO_INCREMENT, id_parent number, id_child number);

create table HIERARCHY_TASKS(id number PRIMARY KEY AUTO_INCREMENT, id_project number, id_task number);

create table TASKS_TO_USERS(id number PRIMARY KEY AUTO_INCREMENT, id_task number, id_user number);


