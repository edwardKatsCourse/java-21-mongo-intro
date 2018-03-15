package com.demo.mongo.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class PersonDbConverter implements AttributeConverter<Person, String> {

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Person attribute) {
        return attribute == null ? null : new ObjectMapper().writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows
    public Person convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(dbData, Person.class);
    }
}
