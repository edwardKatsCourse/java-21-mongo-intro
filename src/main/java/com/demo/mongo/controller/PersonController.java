package com.demo.mongo.controller;

import com.demo.mongo.model.Person;
import com.demo.mongo.model.PersonDB;
import com.demo.mongo.repository.PersonDbRepository;
import com.demo.mongo.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDbRepository personDbRepository;


    @PostMapping("/create")
    public Person add(@RequestBody PersonRequest personRequest) {
        Person person = Person.builder()
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .age(personRequest.getAge())
                .email(personRequest.getEmail())
                .password(personRequest.getPassword())
                .build();

        return personRepository.save(person);
    }

    @PutMapping("/update/{personId}")
    public Person update(@PathVariable("personId") String personId,
                         @RequestParam(value = "first-name", required = false) String firstName,
                         @RequestParam(value = "last-name", required = false) String lastName,
                         @RequestParam(value = "age", required = false) Integer age) {
        Person person = personRepository.findById(personId).orElse(null);
        if (person == null) {
            return null;
        }

        if (firstName != null) {
            person.setFirstName(firstName);
        }

        if (firstName != null) {
            person.setLastName(lastName);
        }

        if (person.getAge() != null) {
            person.setAge(age);
        }

        personRepository.save(person);

        return person;
    }

    @GetMapping("/get")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/get/{personId}")
    public Person getById(@PathVariable("personId") String id) {
        Person person = personRepository.findById(id).orElse(null);

        if (person == null) {
            return null;
        }

        PersonDB personDB = personDbRepository.findByMongoPersonId(id);

        if (personDB == null) {
            personDB = PersonDB.builder()
                    .mongoPersonId(id)
                    .person(person)
                    .build();

        } else {
            personDB.setPerson(person);
        }

        personDbRepository.save(personDB);

        return person;
    }

    @DeleteMapping("/delete/{personId}")
    public void deleteById(@PathVariable("personId") String id) {
        personRepository.deleteById(id);
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(new ObjectMapper().writeValueAsString(Person
                .builder()
                .firstName("James")
                .lastName("Mathews")
                .age(23)
                .build()));

        System.out.println(new ObjectMapper().writeValueAsString(
                PersonRequest.builder()
                .email("peter@site.com")
                .firstName("Peter")
                .lastName("Dale")
                .age(42)
                .password("12345")
                .build()
        ));
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class PersonRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
}
