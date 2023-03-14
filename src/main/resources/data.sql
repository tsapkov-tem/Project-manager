INSERT INTO USERS (ID, LOGIN, PASSWORD, ROLE) values (0, 'admin', '$2a$12$MZylFGrGDAOsuLqSakerYeD25wueKDn0UhjleD2r9AzKbtF6L0aEK', 'ADMIN');
INSERT INTO USERS (ID, LOGIN, PASSWORD, ROLE) values (1, 'user', '$2a$12$nUpmtO.4xddxJO8ez.wLcOCPA0HZ9pVFPiqcZQBo.LZC2f9LqXjqe', 'USER');
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