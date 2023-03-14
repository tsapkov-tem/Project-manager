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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
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
            project.setName(name);
            projectsRepos.save(project);
        }catch (NullPointerException e){
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
        Projects projects = projectsRepos.findById(idProject).orElse(null);
        try {
            assert projects != null;
            projectsRepos.delete(projects);
        }catch (AssertionError e){
            logger.severe("Attempt to delete a non-exist project");
        }
    }

    public void deleteSubProject(Integer idSubProject){
        Projects project = projectsRepos.findById(idSubProject).orElse(null);
        try {
            if(!project.getChild()) {
                List<Integer> idTasks = hierarchyTasksRepos.findAllByIdProject(idSubProject)
                        .stream()
                        .map(HierarchyTasks::getIdTask).toList();
                tasksService.deleteAll(idTasks);

                Integer idParent = hierarchyProjectsRepos.findByIdChild(idSubProject).get(0).getIdParent();
                hierarchyProjectsRepos.deleteByIdChild(idSubProject);
                List<HierarchyProjects> link = hierarchyProjectsRepos.findAllByIdParent(idParent);
                if (link.isEmpty()) {
                    Projects parent = projectsRepos.findById(idParent).orElse(null);
                    if (parent == null) {
                        logger.severe("Table of hierarchy projects has non-deleted rows");
                    }else {
                        parent.setChild(false);
                        projectsRepos.save(parent);
                    }
                }
                hierarchyTasksRepos.deleteAllByIdProject(idSubProject);
                projectsRepos.delete(project);
            }else {
                logger.warning("Attempt to delete a project that has subprojects");
            }
        }catch (NullPointerException e){
            logger.severe("Attempt to delete a non-exist project");
        }
    }

    public Set<Projects> createBranch(Set<Projects> input){
        List<Projects> addedProject = new ArrayList();
        Set<Projects> branchSet = new HashSet<>(input);
        for(Projects project : branchSet){
            addedProject.addAll(getSubProjects(project.getId()));
        }
        branchSet.addAll(addedProject);
        if(branchSet.size()>input.size()){
            branchSet.addAll(createBranch(branchSet));
        }
        return branchSet;
    }

    public void deleteBranch(Integer idParent) {
        try {
            Projects projects;
            Set<Projects> branchSet = new HashSet<>();
            branchSet.add(projectsRepos.findById(idParent).orElse(null));
            List<Integer> branchList = new ArrayList<>(createBranch(new HashSet<>(branchSet)).stream().map(Projects::getId).toList());
            List<Integer> deletedIndexes = new ArrayList<>();
            while (!branchList.isEmpty()) {
                for (int i = 0; i < branchList.size(); i++) {
                    projects = projectsRepos.findById(branchList.get(i)).orElse(null);
                    assert projects != null;
                    if(!projects.getChild()) {
                        if (projects.getParent()) {
                            deleteSubProject(projects.getId());
                            deletedIndexes.add(projects.getId());
                        } else {
                            deleteProject(projects.getId());
                            deletedIndexes.add(projects.getId());
                        }
                    }
                }
                for (Integer index : deletedIndexes) {
                    branchList.remove(index);
                }
                deletedIndexes.clear();
            }
        }catch (NullPointerException e) {
            logger.severe("Attempt to delete branch with non-exist parent");
        }
    }
}
