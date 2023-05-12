package com.example.taskmanager;

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

    @Column(nullable = true,length=300)
    private String description;

    @Column(nullable = false)
    private LocalDate dueDate;
    private LocalDateTime timeCreated;

    public Task() {
    }

    public Task(Integer id, String name, String description, LocalDate dueDate) {
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
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && Objects.equals(timeCreated, task.timeCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dueDate, timeCreated);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duedate=" + dueDate +
                ", timeCreated=" + timeCreated +
                '}';
    }
}
