package com.Mustapha.SpringBootDemo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<AppointmentModel> appointments=new ArrayList<AppointmentModel>();

    public ClientModel() {
    }

    public ClientModel(String firstName, String lastName, String description, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
