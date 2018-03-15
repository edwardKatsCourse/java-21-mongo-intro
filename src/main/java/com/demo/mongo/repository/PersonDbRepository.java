package com.demo.mongo.repository;

import com.demo.mongo.model.PersonDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDbRepository extends JpaRepository<PersonDB, Integer> {

    PersonDB findByMongoPersonId(String mongoPersonId);
}
