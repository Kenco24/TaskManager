package com.example.taskmanager.modules;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @SequenceGenerator(
            name= "task_id_sequence",
            sequenceName = "task_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_sequence"
    )
    private Integer id;

    @Column(nullable = false,length=45)
    private String name;

    @Column(nullable = false,length=100)
    private String task;

    @Column(nullable = true,length=300)
    private String description;

    @Column(nullable = false)
    private LocalDate dueDate;
    private LocalDateTime timeCreated;

    public Task() {
    }

    public Task(Integer id,String task,String name, String description, LocalDate dueDate) {
        this.task=task;
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.timeCreated = (LocalDateTime.now());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return Objects.equals(id, task1.id) && Objects.equals(name, task1.name) && Objects.equals(task, task1.task) && Objects.equals(description, task1.description) && Objects.equals(dueDate, task1.dueDate) && Objects.equals(timeCreated, task1.timeCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, task, description, dueDate, timeCreated);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
