package com.example.basicapplication.controller;

import com.example.basicapplication.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private List<Person> persons = new ArrayList<>();

    @GetMapping
    public List<Person> getAllPersons() {
        return persons;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String addPerson(@RequestBody Person person) {
        persons.add(person);
        return "Person added successfully!";
    }

    @PutMapping("/{id}")
    public String updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        persons.removeIf(person -> person.getId().equals(id));
        updatedPerson.setId(id);
        persons.add(updatedPerson);
        return "Person updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        persons.removeIf(person -> person.getId().equals(id));
        return "Person deleted successfully!";
    }
}

