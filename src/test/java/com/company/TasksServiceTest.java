package com.company;

import com.company.Models.Tasks.Status;
import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.Type;
import com.company.Models.Users.Role;
import com.company.Models.Users.Users;
import com.company.Repos.HierarchyTasksRepos;
import com.company.Repos.TaskToUsersRepos;
import com.company.Repos.TasksRepos;
import com.company.Repos.UsersRepos;
import com.company.Services.TasksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/** 
* TasksService Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 13, 2023</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TasksServiceTest {
    @Autowired
    HierarchyTasksRepos hierarchyTasksRepos;
    @Autowired
    TasksRepos tasksRepos;
    @Autowired
    TasksService tasksService;
    @Autowired
    TaskToUsersRepos tasksToUsersRepos;
    @Autowired
    UsersRepos usersRepos;


/** 
* 
* Method: addTask(String name, String text, Type type, Status status, Integer idAuthor)
* 
*/ 
@Test
public void testAddTask() throws Exception { 
    Tasks tasks = tasksService.addTask("name", "task1", Type.ENGINEER, Status.NEW, 1, 1000);
    assertThat(tasks).isNotNull();

    Tasks addedTask = tasksRepos.findById(tasks.getId()).orElse(null);
    assertThat(addedTask).isNotNull();
    assertThat(addedTask.getId()).isEqualTo(tasks.getId());
    assertThat(addedTask.getText()).isEqualTo(tasks.getText());
    assertThat(addedTask.getStatus()).isEqualTo(tasks.getStatus());
    assertThat(addedTask.getType()).isEqualTo(tasks.getType());

    assertThat(hierarchyTasksRepos.findAllByIdProject(1000).size()).isEqualTo(1);
    assertThat(tasksToUsersRepos.findByIdTask(addedTask.getId()).orElse(null)).isNotNull();
} 

/** 
* 
* Method: updateTypeTask(Integer idTask, Type type) 
* 
*/ 
@Test
public void testUpdateTypeTask() throws Exception {
    Tasks task = tasksService.addTask("name", "task1", Type.ENGINEER, Status.NEW, 1, 1000);
    tasksService.updateTypeTask(task.getId(), Type.MANAGER);
    Tasks updatedTask = tasksRepos.findById(task.getId()).orElse(null);
    assertThat(updatedTask).isNotNull();

    assertThat(updatedTask.getType()).isEqualTo(Type.MANAGER);
    assertThat(updatedTask.getText()).isEqualTo(task.getText());
    assertThat(updatedTask.getStatus()).isEqualTo(task.getStatus());
} 

/** 
* 
* Method: updateStatusTask(Integer idTask, Status status) 
* 
*/ 
@Test
public void testUpdateStatusTask() throws Exception {
    Tasks task = tasksService.addTask("name", "task1", Type.ENGINEER, Status.NEW, 1, 1000);
    tasksService.updateStatusTask(task.getId(), Status.DONE);
    Tasks updatedTask = tasksRepos.findById(task.getId()).orElse(null);
    assertThat(updatedTask).isNotNull();

    assertThat(updatedTask.getType()).isEqualTo(task.getType());
    assertThat(updatedTask.getText()).isEqualTo(task.getText());
    assertThat(updatedTask.getStatus()).isEqualTo(Status.DONE);
} 

/** 
* 
* Method: updateTextTask(Integer idTask, String text) 
* 
*/ 
@Test
public void testUpdateTextTask() throws Exception {
    Tasks task = tasksService.addTask("name", "task1", Type.ENGINEER, Status.NEW, 1, 1000);
    tasksService.updateTextTask(task.getId(), "task2");
    Tasks updatedTask = tasksRepos.findById(task.getId()).orElse(null);
    assertThat(updatedTask).isNotNull();

    assertThat(updatedTask.getType()).isEqualTo(task.getType());
    assertThat(updatedTask.getText()).isEqualTo("task2");
    assertThat(updatedTask.getStatus()).isEqualTo(task.getStatus());
} 

/** 
* 
* Method: getTasksForProject(List<Integer> idTasks) 
* 
*/ 
@Test
public void testGetTasksForProject() throws Exception {
    Tasks tasks = tasksService.addTask("name1","task1", Type.ENGINEER, Status.NEW, 1, 1000);
    Tasks tasks2 = tasksService.addTask("name2", "task2", Type.ENGINEER, Status.NEW, 1, 1000);
    Tasks tasks3 = tasksService.addTask("name3","task3", Type.ENGINEER, Status.NEW, 1, 1000);
    List<Integer> idList = new ArrayList<>();
    idList.add(tasks.getId());
    idList.add(tasks2.getId());
    idList.add(tasks3.getId());
    List<Tasks> tasksList = tasksService.getTasksForProject(idList);
    assertThat(tasksList.size()).isEqualTo(3);
    assertThat(tasksList.get(0)).isEqualTo(tasks);
    assertThat(tasksList.get(1)).isEqualTo(tasks2);
    assertThat(tasksList.get(2)).isEqualTo(tasks3);
} 

/** 
* 
* Method: deleteTask(Integer idTask) 
* 
*/ 
@Test
public void testDeleteTask() throws Exception {
    Users users = new Users();
    users.setId(2);
    users.setLogin("user");
    users.setPassword("password");
    users.setRole(String.valueOf(Role.ADMIN));
    users = usersRepos.save(users);
    Tasks task = tasksService.addTask("name","task1", Type.ENGINEER, Status.NEW, 1, 1000);
    tasksService.deleteTask(task.getId(), users.getId());
    assertThat(tasksRepos.findById(task.getId()).orElse(null)).isNull();
    assertThat(tasksToUsersRepos.findByIdTask(task.getId()).orElse(null)).isNull();
    assertThat(hierarchyTasksRepos.findByIdTask(task.getId()).orElse(null)).isNull();
} 

/** 
* 
* Method: deleteAll(List<Integer> ids) 
* 
*/ 
@Test
public void testDeleteAll() throws Exception {
    Tasks tasks = tasksService.addTask("name1","task1", Type.ENGINEER, Status.NEW, 1, 1000);
    Tasks tasks2 = tasksService.addTask("name2","task2", Type.ENGINEER, Status.NEW, 1, 1000);
    Tasks tasks3 = tasksService.addTask("name3","task3", Type.ENGINEER, Status.NEW, 1, 1000);
    List<Integer> idList = new ArrayList<>();
    idList.add(tasks.getId());
    idList.add(tasks2.getId());
    idList.add(tasks3.getId());
    tasksService.deleteAll(idList);

    assertThat(tasksRepos.findById(tasks.getId()).orElse(null)).isNull();
    assertThat(tasksToUsersRepos.findByIdTask(tasks.getId()).orElse(null)).isNull();
    assertThat(hierarchyTasksRepos.findByIdTask(tasks.getId()).orElse(null)).isNull();

    assertThat(tasksRepos.findById(tasks2.getId()).orElse(null)).isNull();
    assertThat(tasksToUsersRepos.findByIdTask(tasks2.getId()).orElse(null)).isNull();
    assertThat(hierarchyTasksRepos.findByIdTask(tasks2.getId()).orElse(null)).isNull();

    assertThat(tasksRepos.findById(tasks3.getId()).orElse(null)).isNull();
    assertThat(tasksToUsersRepos.findByIdTask(tasks3.getId()).orElse(null)).isNull();
    assertThat(hierarchyTasksRepos.findByIdTask(tasks3.getId()).orElse(null)).isNull();
} 


} 
