package com.company.Controllers;

import com.company.Services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/project")
public class CrudProjectController {

    private final ProjectsService projectsService;

    @Autowired
    public CrudProjectController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @PostMapping("/addProject")
    public void addProject(@RequestParam String name){
        projectsService.addProject(name);
    }

    @PostMapping("/addSubProject")
    public void addSubProject(@RequestParam String name, Integer idParent){
        projectsService.addSubProject(name, idParent);
    }

    @PostMapping("/updateNameProject")
    public void updateNameProject(@RequestParam String name, Integer idProject){
        projectsService.updateNameProject(idProject, name);
    }

    @DeleteMapping("/deleteSubProject")
    public void deleteSubProject(@RequestParam Integer idSubProject){
        projectsService.deleteSubProject(idSubProject);
    }

    @DeleteMapping("/deleteBranch")
    public void deleteBranch(@RequestParam Integer idParent){
        projectsService.deleteBranch(idParent);
    }
}
