package com.Mustapha.SpringBootDemo.responseModels;

import lombok.Data;

@Data
public class BarberResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String description;
    private String fullDescription;

    public BarberResponse(long id, String firstName, String lastName, String description, String fullDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.fullDescription=fullDescription;
    }
}
