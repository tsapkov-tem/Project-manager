package com.company;

import com.company.Models.Projects.HierarchyProjects;
import com.company.Models.Projects.HierarchyTasks;
import com.company.Models.Projects.Projects;
import com.company.Models.Tasks.Status;
import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.Type;
import com.company.Repos.HierarchyProjectsRepos;
import com.company.Repos.HierarchyTasksRepos;
import com.company.Repos.ProjectsRepos;
import com.company.Repos.TasksRepos;
import com.company.Services.ProjectsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/** 
* ProjectsService Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 12, 2023</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProjectsServiceTest {

    @Autowired
    ProjectsService projectsService;
    @Autowired
    ProjectsRepos projectsRepos;
    @Autowired
    HierarchyProjectsRepos hierarchyProjectsRepos;
    @Autowired
    HierarchyTasksRepos hierarchyTasksRepos;
    @Autowired
    TasksRepos tasksRepos;

/** 
* 
* Method: addProject(String name) 
* 
*/ 
@Test
public void testAddProject() throws Exception {
    Projects project = projectsService.addProject("Project2");
    assertThat(project).isNotNull();
    assertThat(project.getName()).isEqualTo("Project2");
    Projects addedProject = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(addedProject).isNotNull();
    assertThat(addedProject.getName()).isEqualTo("Project2");
} 

/** 
* 
* Method: updateNameProject(Integer idProject, String name) 
* 
*/ 
@Test
public void testUpdateNameProject() throws Exception {
    Projects project = projectsService.addProject("Project1");
    Projects updatedProject = projectsService.updateNameProject(project.getId(), "Project2");
    assertThat(updatedProject).isNotNull();
    assertThat(updatedProject.getName()).isEqualTo("Project2");
    project = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(project).isNotNull();
    assertThat(project.getName()).isEqualTo("Project2");
} 

/** 
* 
* Method: addSubProject(String name, Integer idParent) 
* 
*/ 
@Test
public void testAddSubProject() throws Exception {
    Projects project = projectsService.addProject("Project1");
    assertThat(project.getChild()).isEqualTo(false);
    assertThat(project.getParent()).isEqualTo(false);

    Projects addedSubProject = projectsService.addSubProject("SubProject1", project.getId());
    assertThat(addedSubProject).isNotNull();

    project = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(project).isNotNull();
    assertThat(project.getChild()).isEqualTo(true);

    assertThat(addedSubProject.getParent()).isEqualTo(true);
    assertThat(addedSubProject.getChild()).isEqualTo(false);

    List<HierarchyProjects> hierarchyProjects = hierarchyProjectsRepos.findAllByIdParent(project.getId());
    assertThat(hierarchyProjects.size()).isGreaterThan(0);
    assertThat(hierarchyProjects.get(0).getIdChild()).isEqualTo(addedSubProject.getId());
    assertThat(hierarchyProjects.get(0).getIdParent()).isEqualTo(project.getId());
} 

/** 
* 
* Method: getAllProjects() 
* 
*/ 
@Test
public void testGetAllProjects() throws Exception {
    Projects project1 = projectsService.addProject("Project1");
    Projects project2 = projectsService.addProject("Project2");
    Projects project3 = projectsService.addProject("Project3");
    List<Projects> projects = projectsService.getAllProjects();

    assertThat(projects.size()).isEqualTo(3);
    assertThat(projects.get(0).getName()).isEqualTo("Project1");
    assertThat(projects.get(1).getName()).isEqualTo("Project2");
    assertThat(projects.get(2).getName()).isEqualTo("Project3");

    assertThat(projects.get(0).getId()).isEqualTo(project1.getId());
    assertThat(projects.get(1).getId()).isEqualTo(project2.getId());
    assertThat(projects.get(2).getId()).isEqualTo(project3.getId());
} 

/** 
* 
* Method: getTasksForProject(Integer idProject) 
* 
*/ 
@Test
public void testGetTasksForProject() throws Exception {
    Projects project = projectsService.addProject("Project1");

    HierarchyTasks hierarchyTasks = new HierarchyTasks();
    hierarchyTasks.setIdTask(1);
    hierarchyTasks.setIdProject(project.getId());
    hierarchyTasksRepos.save(hierarchyTasks);

    HierarchyTasks hierarchyTasks2 = new HierarchyTasks();
    hierarchyTasks2.setIdTask(2);
    hierarchyTasks2.setIdProject(project.getId());
    hierarchyTasksRepos.save(hierarchyTasks2);

    Tasks tasks = new Tasks();
    tasks.setId(1);
    tasks.setText("Task1");
    tasks.setStatus(Status.DONE);
    tasks.setType(Type.ENGINEER);
    tasksRepos.save(tasks);

    Tasks tasks2 = new Tasks();
    tasks2.setId(2);
    tasks2.setText("Task2");
    tasks2.setStatus(Status.DONE);
    tasks2.setType(Type.ENGINEER);
    tasksRepos.save(tasks2);

    List<Tasks> tasksList = projectsService.getTasksForProject(project.getId());
    assertThat(tasksList.size()).isEqualTo(2);

    assertThat(tasksList.get(0).getId()).isEqualTo(1);
    assertThat(tasksList.get(0).getText()).isEqualTo("Task1");

    assertThat(tasksList.get(1).getId()).isEqualTo(2);
    assertThat(tasksList.get(1).getText()).isEqualTo("Task2");

} 

/** 
* 
* Method: getSubProjects(Integer idParent) 
* 
*/ 
@Test
public void testGetSubProjects() throws Exception {
    Projects project = projectsService.addProject("Project1");
    Projects addedSubProject = projectsService.addSubProject("SubProject1", project.getId());
    Projects addedSubProject2 = projectsService.addSubProject("SubProject2", project.getId());

    List<Projects> subProjects = projectsService.getSubProjects(project.getId());

    assertThat(subProjects.size()).isEqualTo(2);
    assertThat(subProjects.get(0).getId()).isEqualTo(addedSubProject.getId());
    assertThat(subProjects.get(0).getName()).isEqualTo(addedSubProject.getName());

    assertThat(subProjects.get(1).getId()).isEqualTo(addedSubProject2.getId());
    assertThat(subProjects.get(1).getName()).isEqualTo(addedSubProject2.getName());
} 

/** 
* 
* Method: deleteSubProject(Integer idSubProject) 
* 
*/ 
@Test
public void testDeleteSubProject() throws Exception {
    Projects project = projectsService.addProject("Project1");
    Projects addedSubProject = projectsService.addSubProject("SubProject1", project.getId());

    Tasks tasks = new Tasks();
    tasks.setId(1);
    tasks.setText("Task1");
    tasks.setStatus(Status.DONE);
    tasks.setType(Type.ENGINEER);
    tasksRepos.save(tasks);

    HierarchyTasks hierarchyTasks = new HierarchyTasks();
    hierarchyTasks.setIdTask(1);
    hierarchyTasks.setIdProject(addedSubProject.getId());
    hierarchyTasksRepos.save(hierarchyTasks);

    project = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(project).isNotNull();
    assertThat(project.getChild()).isEqualTo(true);

    projectsService.deleteSubProject(addedSubProject.getId());

    List<Tasks> tasksList = projectsService.getTasksForProject(addedSubProject.getId());
    assertThat(tasksList.size()).isEqualTo(0);
    assertThat(hierarchyTasksRepos.findAllByIdProject(addedSubProject.getId()).size()).isEqualTo(0);

    assertThat(hierarchyProjectsRepos.findByIdChild(addedSubProject.getId())).isNull();

    addedSubProject = projectsRepos.findById(addedSubProject.getId()).orElse(null);
    assertThat(addedSubProject).isNull();
    List<HierarchyProjects> list =  hierarchyProjectsRepos.findAllByIdParent(project.getId());
    assertThat(list.size()).isEqualTo(0);

    project = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(project.getChild()).isEqualTo(false);
} 

/** 
* 
* Method: deleteBranch(Integer idParent) 
* 
*/ 
@Test
public void testDeleteBranch() throws Exception {
    Projects project = projectsService.addProject("Project1");
    Projects addedSubProject = projectsService.addSubProject("SubProject1", project.getId());

    Integer idProject  = project.getId();

    Tasks tasks = new Tasks();
    tasks.setId(1);
    tasks.setText("Task1");
    tasks.setStatus(Status.DONE);
    tasks.setType(Type.ENGINEER);
    tasksRepos.save(tasks);

    HierarchyTasks hierarchyTasks = new HierarchyTasks();
    hierarchyTasks.setIdTask(1);
    hierarchyTasks.setIdProject(addedSubProject.getId());
    hierarchyTasksRepos.save(hierarchyTasks);

    projectsService.deleteBranch(project.getId());

    List<Tasks> tasksList = projectsService.getTasksForProject(addedSubProject.getId());
    assertThat(tasksList.size()).isEqualTo(0);
    assertThat(hierarchyTasksRepos.findAllByIdProject(addedSubProject.getId()).size()).isEqualTo(0);

    addedSubProject = projectsRepos.findById(addedSubProject.getId()).orElse(null);
    assertThat(addedSubProject).isNull();

    project = projectsRepos.findById(project.getId()).orElse(null);
    assertThat(project).isNull();

    List<HierarchyProjects> list =  hierarchyProjectsRepos.findAllByIdParent(idProject);
    assertThat(list.size()).isEqualTo(0);
} 


} 
