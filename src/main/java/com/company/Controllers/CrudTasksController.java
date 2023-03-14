package com.company.Controllers;

import com.company.Models.Tasks.Status;
import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.Type;
import com.company.Services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CrudTasksController {
    private final TasksService tasksService;

    @Autowired
    public CrudTasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }


    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/addTask") //add task for project or subproject
    public Tasks addTask(@RequestParam String name,  String text, Type type, Status status, Integer idAuthor, Integer idProject){
        return tasksService.addTask(name, text, type, status, idAuthor, idProject);
    }


    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @PostMapping("/updateTypeTask") //change type of task
    public Tasks updateTypeTask(@RequestParam Type type, Integer idTask){
        return tasksService.updateTypeTask(idTask, type);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @PostMapping("/updateNameTask") //change name of task
    public Tasks updateNameTask(@RequestParam String name, Integer idTask){
        return tasksService.updateNameTask(idTask, name);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @PostMapping("/updateStatusTask") //change status of task
    public Tasks updateStatusTask(@RequestParam Status status, Integer idTask){
        return tasksService.updateStatusTask(idTask, status);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @PostMapping("/updateTextTask") //change text for task
    public Tasks updateTextTask(@RequestParam String text, Integer idTask){
        return tasksService.updateTextTask(idTask, text);
    }

    @PreAuthorize("hasAnyAuthority('admin', 'user')")
    @DeleteMapping("/deleteTask") //delete one task
    public void deleteTask(@RequestParam Integer idTask, Integer idUser){
        tasksService.deleteTask(idTask, idUser);
    }

}
