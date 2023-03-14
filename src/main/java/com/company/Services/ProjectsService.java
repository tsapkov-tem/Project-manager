package com.company.Services;

import com.company.Models.Projects.HierarchyProjects;
import com.company.Models.Projects.HierarchyTasks;
import com.company.Models.Projects.Projects;
import com.company.Models.Tasks.Tasks;
import com.company.Repos.HierarchyProjectsRepos;
import com.company.Repos.HierarchyTasksRepos;
import com.company.Repos.ProjectsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProjectsService {
    private static final Logger logger = Logger.getLogger(ProjectsService.class.getName());
    private final HierarchyProjectsRepos hierarchyProjectsRepos;
    private final HierarchyTasksRepos hierarchyTasksRepos;
    private final ProjectsRepos projectsRepos;
    private final TasksService tasksService;

    @Autowired
    public ProjectsService(HierarchyProjectsRepos hierarchyProjectsRepos, HierarchyTasksRepos hierarchyTasksRepos, ProjectsRepos projectsRepos, TasksService tasksService) {
        this.hierarchyProjectsRepos = hierarchyProjectsRepos;
        this.hierarchyTasksRepos = hierarchyTasksRepos;
        this.projectsRepos = projectsRepos;
        this.tasksService = tasksService;
    }

    public Projects addProject(String name){
        Projects project = new Projects();
        project.setName(name);
        project.setChild(false);
        project.setParent(false);
        return projectsRepos.save(project);
    }

    public Projects updateNameProject(Integer idProject, String name){
        Projects project = projectsRepos.findById(idProject).orElse(null);
        try {
            assert project != null;
            project.setName(name);
            projectsRepos.save(project);
        }catch (AssertionError e){
            logger.severe("Attempt to update a non-exist project");
        }
        return project;
    }

    public Projects addSubProject(String name, Integer idParent){
        Projects project = null;
        try {
            Projects parent = projectsRepos.findById(idParent).orElse(null);
            assert parent != null;
            parent.setChild(true);
            projectsRepos.save(parent);
            project = new Projects();
            project.setName(name);
            project.setChild(false);
            project.setParent(true);
            projectsRepos.save(project);
            HierarchyProjects hierarchyProjects = new HierarchyProjects();
            hierarchyProjects.setIdChild(project.getId());
            hierarchyProjects.setIdParent(idParent);
            hierarchyProjectsRepos.save(hierarchyProjects);
        }catch (AssertionError e){
            logger.severe("Parent not found when creating subProject");
        }
        return project;
    }

    public List<Projects> getAllProjects(){
        return projectsRepos.findAllByParent(false); //Project is project if it hasn't parent
    }

    public List<Tasks> getTasksForProject(Integer idProject){
        return tasksService.getTasksForProject(
                hierarchyTasksRepos.findAllByIdProject(idProject)
                        .stream()
                        .map(HierarchyTasks::getIdTask).toList()
        );
    }

    public List<Projects> getSubProjects(Integer idParent){
        List<Integer> idChildren = hierarchyProjectsRepos.findAllByIdParent(idParent)
                .stream()
                .map(HierarchyProjects::getIdChild)
                .toList();
        return projectsRepos.findAllByIdIn(idChildren);
    }

    public void deleteProject(Integer idProject){
        projectsRepos.deleteById(idProject);
    }

    public void deleteSubProject(Integer idSubProject){
        Projects project = projectsRepos.findById(idSubProject).orElse(null);
        try {
            assert !project.getChild();
            List<Integer> idTasks = hierarchyTasksRepos.findAllByIdProject(idSubProject)
                    .stream()
                    .map(HierarchyTasks::getIdTask).toList();
            tasksService.deleteAll(idTasks);
            Integer idParent = hierarchyProjectsRepos.findByIdChild(idSubProject).getIdParent();
            hierarchyProjectsRepos.removeByIdChild(idSubProject);
            if(hierarchyProjectsRepos.findAllByIdParent(idParent).isEmpty()){
                Projects parent = projectsRepos.findById(idParent).orElse(null);
                if(parent == null){
                    logger.severe("Table of hierarchy projects has non-deleted rows");
                }
                parent.setChild(false);
                projectsRepos.save(parent);
            }
            hierarchyTasksRepos.removeAllByIdProject(idSubProject);
            projectsRepos.delete(project);
        }catch (AssertionError e){
            logger.warning("Attempt to delete a project that has subprojects");
        }catch (NullPointerException e){
            logger.severe("Attempt to delete a non-exist project");
        }
    }

    public void deleteBranch(Integer idParent) {
        try {
            List<Projects> branchList = getSubProjects(idParent);
            branchList.add(projectsRepos.findById(idParent).orElse(null));
            List<Projects> deleteList = new ArrayList<>();
            while (!branchList.isEmpty()) {
                deleteList.addAll(branchList);
                for (Projects project : deleteList) {
                    if (!project.getChild()) {
                        if(project.getId().equals(idParent)){
                            deleteProject(idParent);
                        }else {
                            deleteSubProject(project.getId());
                        }
                        branchList.remove(project);
                    } else {
                        List<Projects> children = getSubProjects(project.getId());
                        if (children.isEmpty()) {
                            project.setChild(false);
                            projectsRepos.save(project);
                        }
                        branchList.addAll(children);
                    }
                }
                deleteList.clear();
            }
        }catch (NullPointerException e){
            logger.severe("Attempt to delete branch with non-exist parent");
        }
    }
}
