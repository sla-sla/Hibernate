package com.example.hibenate.controller;

import com.example.hibenate.entity.Person;
import com.example.hibenate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.hibenate.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Получение списка людей по городу
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personRepository.findByCityOfLiving(city);
    }

    // Получение списка людей младше определённого возраста
    @GetMapping("/by-age")
    public List<Person> getPersonsByAge(@RequestParam int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    // Получение человека по имени и фамилии
    @GetMapping("/by-name-and-surname")
    public Optional<Person> getPersonByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    // Создание нового человека
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    // Обновление данных человека
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));

        person.setName(personDetails.getName());
        person.setSurname(personDetails.getSurname());
        person.setAge(personDetails.getAge());
        person.setPhoneNumber(personDetails.getPhoneNumber());
        person.setCityOfLiving(personDetails.getCityOfLiving());

        return personRepository.save(person);
    }

    // Удаление человека
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
        personRepository.delete(person);
    }
}
