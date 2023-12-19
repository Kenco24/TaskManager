package com.example.taskmanager.controllers;

import com.example.taskmanager.modules.Person;
import com.example.taskmanager.repositories.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/myAPI/person")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    record NewPersonRequest(String name, String phone, String email, String location) {
    }

    @PostMapping
    public void addPerson(@RequestBody PersonController.NewPersonRequest request) {
        try {
            if (request.name.length() > 45 || request.location.length() > 100 || !isValidPhoneNumber(request.phone)) {
                throw new DataIntegrityViolationException("Invalid person data");
            }

            Person person = new Person();
            person.setName(request.name);
            person.setPhone(request.phone);
            person.setEmail(request.email);
            person.setLocation(request.location);

            personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Invalid person data: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage, e);
        } catch (Exception e) {
            String errorMessage = "An error occurred: " + e.getMessage();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, e);
        }
    }

    @DeleteMapping("{personId}")
    public void deletePerson(@PathVariable("personId") Integer id) {
        personRepository.deleteById(id);
    }

    @PutMapping("{personId}")
    public void editPerson(@PathVariable("personId") Integer id, @RequestBody PersonController.NewPersonRequest request) {
        Person personToUpdate = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Person Id:" + id));

        personToUpdate.setName(request.name);
        personToUpdate.setPhone(request.phone);
        personToUpdate.setEmail(request.email);
        personToUpdate.setLocation(request.location);

        personRepository.save(personToUpdate);
    }

    @GetMapping("{personId}")
    public Optional<Person> retrievePerson(@PathVariable("personId") Integer id) {
        return personRepository.findById(id);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Use a regex pattern to check if the phone number contains only digits and optional spaces
        String regex = "^[0-9\\s]+$";
        return Pattern.matches(regex, phoneNumber);
    }
}
