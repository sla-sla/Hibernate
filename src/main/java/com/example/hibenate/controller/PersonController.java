package com.example.hibenate.controller;

import com.example.hibenate.entity.Person;
import com.example.hibenate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personRepository.findByCityOfLiving(city);
    }
}