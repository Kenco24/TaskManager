package com.example.taskmanager.controllers;

import com.example.taskmanager.modules.Task;
import com.example.taskmanager.repositories.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/myAPI/tasks")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            Task t = new Task();
            t.setTask(task.getTask());
            t.setId(task.getId());
            t.setName(task.getName());
            t.setDueDate(task.getDueDate());
            t.setTimeCreated(task.getTimeCreated());
            String str=task.getDescription();
            t.setDescription(str);


            result.add(t);

        }
        return result;
    }

    record NewTaskRequest(String name, String task,String description, LocalDate dueDate){
    }

    @PostMapping
    public void addTask(@RequestBody TaskController.NewTaskRequest request) {
        try {

            if (request.task.length() > 100 || request.name.length() > 45 ||
                    (request.description != null && request.description.length() > 300) ||
                    request.dueDate == null) {
                throw new DataIntegrityViolationException("Invalid task data");
            }
            Task task = new Task();
            task.setTask(request.task);
            task.setName(request.name);
            task.setDescription(request.description);
            task.setDueDate(request.dueDate);
            task.setTimeCreated(LocalDateTime.now());
            taskRepository.save(task);

        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Invalid task data: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage, e);
        } catch (Exception e) {
            String errorMessage = "An error occurred: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, e);
        }
    }
    @DeleteMapping("{taskId}")
    public void deleteTask(@PathVariable("taskId") Integer id){
        taskRepository.deleteById(id);
    }

    @PutMapping("{taskId}")
    public void editTask(@PathVariable("taskId") Integer id, @RequestBody TaskController.NewTaskRequest request){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Task Id:" + id));
        taskToUpdate.setName(request.name);
        taskToUpdate.setTask(request.task);
        taskToUpdate.setDescription(request.description);
        taskToUpdate.setDueDate(request.dueDate);
        taskRepository.save(taskToUpdate);
    }

    @GetMapping("{taskId}")
    public Optional<Task> retreiveTask(@PathVariable("taskId") Integer id)
    {
        return taskRepository.findById(id);
    }
}
