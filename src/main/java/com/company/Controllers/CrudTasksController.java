package com.company.Controllers;

import com.company.Models.Tasks.Status;
import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.Type;
import com.company.Services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/task")
public class CrudTasksController {
    private final TasksService tasksService;

    @Autowired
    public CrudTasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }


    @PostMapping("/addTask")
    public Tasks addProject(@RequestParam String text, Type type, Status status, Integer idAuthor, Integer idProject){
        return tasksService.addTask(text, type, status, idAuthor, idProject);
    }


    @PostMapping("/updateTypeTask")
    public Tasks updateTypeProject(@RequestParam Type type, Integer idTask){
        return tasksService.updateTypeTask(idTask, type);
    }

    @PostMapping("/updateStatusTask")
    public Tasks updateNameProject(@RequestParam Status status, Integer idTask){
        return tasksService.updateStatusTask(idTask, status);
    }

    @PostMapping("/updateTextTask")
    public Tasks updateNameProject(@RequestParam String text, Integer idTask){
        return tasksService.updateTextTask(idTask, text);
    }

    @DeleteMapping("/deleteTask")
    public void deleteSubProject(@RequestParam Integer idTask){
        tasksService.deleteTask(idTask);
    }

}
