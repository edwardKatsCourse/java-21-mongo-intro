package com.demo.mongo.repository;

import com.demo.mongo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
    Person findByFirstNameAndLastName(String s1, String s2);
}
