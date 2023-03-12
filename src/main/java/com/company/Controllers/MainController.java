package com.company.Controllers;

import com.company.Services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    private final ProjectsService projectsService;

    @Autowired
    public MainController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

}
