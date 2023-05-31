package com.example.taskmanager.repositories;

import com.example.taskmanager.modules.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {

}
