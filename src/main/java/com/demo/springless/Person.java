package com.demo.springless;

import lombok.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Person {

    private String id;
    private String firstName;
    private String lastName;
    private Integer age;

    public Map<String, Object> getAsMap() {
        return new LinkedHashMap<String, Object>() {{
            put("id", id);
            put("firstName", firstName);
            put("lastName", lastName);
            put("age", age);
        }};
    }
}
