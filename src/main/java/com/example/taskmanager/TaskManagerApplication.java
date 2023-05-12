package com.example.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/tasks")
public class TaskManagerApplication {

    private final TaskRepository taskRepository;

    public TaskManagerApplication(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    /*@GetMapping
    public List<Task> getTasks(){
        return taskRepository.findAll();
    } */

    @GetMapping
    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            Task t = new Task();
            t.setId(task.getId());
            t.setName(task.getName());
            t.setDueDate(task.getDueDate());
            t.setTimeCreated(task.getTimeCreated());
            String str=task.getDescription();
            String first20Chars = str.substring(0, 20);

            t.setDescription(first20Chars + "...    To view description retrieve a certain task by entering \nlocalhost:8080/api/v1/tasks/{id}");
            result.add(t);

        }
        return result;
    }

    record NewTaskRequest(String name, String description, LocalDate dueDate){
    }

    @PostMapping
    public void addTask(@RequestBody NewTaskRequest request)
    {
        Task task = new Task();
        task.setName(request.name);
        task.setDescription(request.description);
        task.setDueDate(request.dueDate);
        task.setTimeCreated(LocalDateTime.now());
        taskRepository.save(task);
    }
    @DeleteMapping("{taskId}")
    public void deleteTask(@PathVariable ("taskId") Integer id){
        taskRepository.deleteById(id);
    }

    @PutMapping("{taskId}")
    public void editTask(@PathVariable("taskId") Integer id, @RequestBody NewTaskRequest request){
        Task taskToUpdate = taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Task Id:" + id));
        taskToUpdate.setName(request.name);
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
