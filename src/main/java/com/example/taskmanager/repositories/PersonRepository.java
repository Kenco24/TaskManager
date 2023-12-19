package com.example.taskmanager.repositories;

import com.example.taskmanager.modules.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

}
