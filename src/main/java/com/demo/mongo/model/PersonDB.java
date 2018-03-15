package com.demo.mongo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_DB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    @Column(unique = true)
    private String mongoPersonId;

    @Convert(converter = PersonDbConverter.class)
    private Person person;
}
