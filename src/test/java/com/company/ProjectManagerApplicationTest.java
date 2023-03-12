package com.company;

import com.company.Repos.*;
import com.company.Services.ProjectsService;
import com.company.Services.TasksService;
import com.company.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerApplicationTest { 

    @Autowired
    HierarchyTasksRepos hierarchyTasksRepos;
    @Autowired
    HierarchyProjectsRepos hierarchyProjectsRepos;
    @Autowired
    ProjectsRepos projectsRepos;
    @Autowired
    TasksRepos tasksRepos;
    @Autowired
    TaskToUsersRepos taskToUsersRepos;
    @Autowired
    UsersRepos usersRepos;
    @Autowired
    ProjectsService projectsService;
    @Autowired
    TasksService tasksService;
    @Autowired
    UserService userService;

@Test
public void testMain() throws Exception { 
    assertThat(hierarchyTasksRepos).isNotNull();
    assertThat(hierarchyProjectsRepos).isNotNull();
    assertThat(projectsRepos).isNotNull();
    assertThat(tasksRepos).isNotNull();
    assertThat(taskToUsersRepos).isNotNull();
    assertThat(usersRepos).isNotNull();
    assertThat(projectsService).isNotNull();
    assertThat(tasksService).isNotNull();
    assertThat(userService).isNotNull();
} 


} 
