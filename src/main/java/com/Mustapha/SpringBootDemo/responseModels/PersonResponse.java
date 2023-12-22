package com.Mustapha.SpringBootDemo.responseModels;

import lombok.Data;

@Data
public class PersonResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String description;

    public PersonResponse(long id, String firstName, String lastName, String description) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }
}
