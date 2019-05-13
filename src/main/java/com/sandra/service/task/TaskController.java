package com.sandra.service.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @RequestMapping("/")
    public @ResponseBody String usage() {
        return "Provides Following CRUD Operations for Tasks";
    }

    @GetMapping(path = "/admin/tasks")
    public @ResponseBody Iterable<Task> getAllTasks() {
        return repository.findAll();
    }

    @PostMapping(path = "/users/tasks")
    public @ResponseBody Task createTask(
            @RequestParam("userId") Integer userId,
            @RequestBody Task task){
        Task task1= new Task();
        task1.setTitle(task.getTitle());
        task.setUserId(userId);
        return  repository.save(task);
    }

    @GetMapping(path = "/users/tasks")
    public @ResponseBody List<Task> getTasksForUser( @RequestParam("userId") Integer userId){
       List<Task> tasks=  repository.findByUserId(userId);
       if(tasks.isEmpty()){
           throw new UserNotFoundException();
       }
       return tasks;
    }

    @GetMapping(path = "/users/tasks/{taskId}")
    public @ResponseBody Task getTaskForUser(@PathVariable Integer taskId,  @RequestParam("userId") Integer userId){

        Task t= repository.findByIdAndUserId(taskId,userId );
        if(t==null){
            throw new TaskNotFoundException();
        }
        return t;
    }

    @DeleteMapping(path = "/users/tasks/{taskId}")
    public @ResponseBody Task deleteTaskForUser(@PathVariable Integer taskId,  @RequestParam("userId") Integer userId){
        Task t= repository.findByIdAndUserId(taskId,userId );
        if(t==null){
            throw new TaskNotFoundException();
        }
        repository.delete(t);
        return t;
    }

    @PutMapping(path = "/users/tasks/{taskId}")
    public Task updateTaskForUser(@PathVariable Integer taskId,
                                  @RequestParam("userId") Integer userId,
                                  @RequestBody Task task){
        Task t= repository.findByIdAndUserId(taskId,userId );

        if(t==null){
            throw new TaskNotFoundException();
        }
        t.setId(taskId);
        t.setDescription(task.getDescription());
        t.setDueDate(task.getDueDate());
        t.setReminder(task.getReminder());
        t.setTitle(task.getTitle());
        t.setDone(task.getDone());
        t.setCompletionDate(task.getCompletionDate());
        repository.save(t);
        return t;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task or user not found")
    public  class TaskNotFoundException extends RuntimeException{
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    public  class UserNotFoundException extends RuntimeException{
    }

}
