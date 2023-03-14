package com.company.Services;

import com.company.Models.Projects.HierarchyTasks;
import com.company.Models.Tasks.Status;
import com.company.Models.Tasks.Tasks;
import com.company.Models.Tasks.TasksToUsers;
import com.company.Models.Tasks.Type;
import com.company.Models.Users.Role;
import com.company.Models.Users.Users;
import com.company.Repos.HierarchyTasksRepos;
import com.company.Repos.TaskToUsersRepos;
import com.company.Repos.TasksRepos;
import com.company.Repos.UsersRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TasksService {
    private final TaskToUsersRepos taskToUsersRepos;
    private final TasksRepos tasksRepos;
    private final HierarchyTasksRepos hierarchyTasksRepos;
    private final UsersRepos usersRepos;
    private final static Logger logger = Logger.getLogger(TasksService.class.getName());

    @Autowired
    public TasksService(TaskToUsersRepos taskToUsersRepos, TasksRepos tasksRepos, HierarchyTasksRepos hierarchyTasksRepos, UsersRepos usersRepos) {
        this.taskToUsersRepos = taskToUsersRepos;
        this.tasksRepos = tasksRepos;
        this.hierarchyTasksRepos = hierarchyTasksRepos;
        this.usersRepos = usersRepos;
    }

    public Tasks addTask(String name, String text, Type type, Status status,Integer idAuthor, Integer idProject){
        Tasks task = new Tasks();
        task.setText(text);
        task.setType(String.valueOf(type));
        task.setStatus(String.valueOf(status));
        Date date = new Date();
        task.setDate_change(String.valueOf(date));
        task.setDate_create(String.valueOf(date));
        task.setName(name);
        task = tasksRepos.save(task);
        TasksToUsers tasksToUsers = new TasksToUsers();
        tasksToUsers.setIdTask(task.getId());
        tasksToUsers.setIdUser(idAuthor);
        taskToUsersRepos.save(tasksToUsers);
        HierarchyTasks hierarchyTasks = new HierarchyTasks();
        hierarchyTasks.setIdTask(task.getId());
        hierarchyTasks.setIdProject(idProject);
        hierarchyTasksRepos.save(hierarchyTasks);
        return task;
    }

    public Tasks updateNameTask(Integer idTask, String name) {
        Tasks task = tasksRepos.findById(idTask).orElse(null);
        try {
            assert task != null;
            task.setName(name);
            tasksRepos.save(task);
        }catch (AssertionError e){
            logger.severe("Attempt to update a non-exist task");
        }
        return task;
    }

    public Tasks updateTypeTask(Integer idTask, Type type){
        Tasks task = tasksRepos.findById(idTask).orElse(null);
        try {
            assert task != null;
            task.setType(String.valueOf(type));
            tasksRepos.save(task);
        }catch (AssertionError e){
            logger.severe("Attempt to update a non-exist task");
        }
        return task;
    }

    public Tasks updateStatusTask(Integer idTask, Status status){
        Tasks task = tasksRepos.findById(idTask).orElse(null);
        try {
            assert task != null;
            task.setStatus(String.valueOf(status));
            Date date = new Date();
            task.setDate_change(String.valueOf(date));
            tasksRepos.save(task);
        }catch (AssertionError e){
            logger.severe("Attempt to update a non-exist task");
        }
        return task;
    }

    public Tasks updateTextTask(Integer idTask, String text){
        Tasks task = tasksRepos.findById(idTask).orElse(null);
        try {
            assert task != null;
            task.setText(text);
            tasksRepos.save(task);
        }catch (AssertionError e){
            logger.severe("Attempt to update a non-exist task");
        }
        return task;
    }

    public List<Tasks> getTasksForProject(List<Integer> idTasks){
        return tasksRepos.findAllByIdIn(idTasks);
    }

    public void deleteTask(Integer idTask, Integer idUser){
        Users user = usersRepos.findById(idUser).orElse(null);
        try {
            assert user != null;
            Integer idAuthor = taskToUsersRepos.findByIdTask(idTask).orElse(null).getIdUser();
            if(user.getRole().equals(Role.ADMIN) || idAuthor.equals(user.getId())) {
                taskToUsersRepos.removeByIdTask(idTask);
                hierarchyTasksRepos.deleteByIdTask(idTask);
                tasksRepos.deleteById(idTask);
            }
        }catch (AssertionError e){
         logger.warning("Id of non-exist user");
        }catch (NullPointerException e){
            logger.warning("There is not link between task and user");
        }
    }

    public void deleteAll(List<Integer> ids){
        taskToUsersRepos.deleteAllByIdTaskIn(tasksRepos.findAllByIdIn(ids).stream().map(Tasks::getId).toList());
        hierarchyTasksRepos.deleteAllByIdTaskIn(ids);
        tasksRepos.deleteAllByIdIn(ids);
    }

}
