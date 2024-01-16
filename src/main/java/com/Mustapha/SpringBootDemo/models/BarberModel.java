package com.Mustapha.SpringBootDemo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BarberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String description;
    private String fullDescription;
    @OneToMany(mappedBy = "barber", cascade = CascadeType.REMOVE)
    private List<AppointmentModel> appointments=new ArrayList<AppointmentModel>();

    public BarberModel() {
    }

    public BarberModel(String firstName, String lastName, String description, String fullDescription) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.fullDescription = fullDescription;
    }

    public void add_Appointments_barber(AppointmentModel appointment) {
        this.appointments.add(appointment);
    }
}
