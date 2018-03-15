package com.demo.mongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Document(collection = "persons_2")
public class Person {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Integer age;

    @Indexed(unique = true)
    private String email;

    @Transient
    private String password;
}
