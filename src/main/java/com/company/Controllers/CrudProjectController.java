package com.company.Controllers;

import com.company.Models.Projects.Projects;
import com.company.Models.Tasks.Tasks;
import com.company.Services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class CrudProjectController {

    private final ProjectsService projectsService;

    @Autowired
    public CrudProjectController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addProject") //add new project without any subproject
    public Projects addProject(@RequestParam String name){
        return projectsService.addProject(name);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addSubProject") //add subproject for exist project
    public Projects addSubProject(@RequestParam String name, Integer idParent){
        return projectsService.addSubProject(name, idParent);
    }


    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/updateNameProject") //projects has only name
    public Projects updateNameProject(@RequestParam String name, Integer idProject){
        return projectsService.updateNameProject(idProject, name);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/getAllProjects") //get all head of even branch, then from heads we can get subprojects
    public List<Projects> getAllProjects(){
        return projectsService.getAllProjects();
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/getSubProjects") //get next step of project hierarchy
    public List<Projects> getSubProjects(@RequestParam Integer idParent){
        return projectsService.getSubProjects(idParent);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @GetMapping("/getTasksForProject") //get tasks for only one project or subproject
    public List<Tasks> getTasksForProject(@RequestParam Integer idProject){
        return projectsService.getTasksForProject(idProject);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/deleteSubProject") //delete subproject with task for it, but only subprojects that hasn't child
    public void deleteSubProject(@RequestParam Integer idSubProject){
        projectsService.deleteSubProject(idSubProject);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/deleteBranch") //delete several subprojects (also with project, or no) with their tasks
    public void deleteBranch(@RequestParam Integer idParent){
        projectsService.deleteBranch(idParent);
    }
}
