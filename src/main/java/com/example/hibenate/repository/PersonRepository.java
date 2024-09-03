package com.example.hibenate.repository;

import com.example.hibenate.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Метод для поиска людей по городу
    List<Person> findByCityOfLiving(String city);

    // Метод для поиска людей младше определённого возраста, отсортированных по возрастанию возраста
    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    // Метод для поиска человека по имени и фамилии
    Optional<Person> findByNameAndSurname(String name, String surname);
}
