package com.Mustapha.SpringBootDemo.models;

import com.Mustapha.SpringBootDemo.security.AppUser;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String description;
    @OneToOne(mappedBy = "personModel", cascade = CascadeType.REMOVE)
    private AppUser appUser;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<AppointmentModel> appointments_client=new ArrayList<AppointmentModel>();
    @OneToMany(mappedBy = "barber", cascade = CascadeType.REMOVE)
    private List<AppointmentModel> appointments_barber=new ArrayList<AppointmentModel>();
    public PersonModel() {
    }

    public PersonModel(String firstName, String lastName, String description, AppUser appUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.appUser = appUser;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<AppointmentModel> getAppointments_client() {
        return appointments_client;
    }

    public void add_Appointment_client(AppointmentModel appointment_client) {
        this.appointments_client.add(appointment_client) ;
    }

    public List<AppointmentModel> getAppointments_barber() {
        return appointments_barber;
    }

    public void add_Appointments_barber(AppointmentModel appointment_barber) {
        this.appointments_barber.add(appointment_barber);
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", appUser=" + appUser +
                '}';
    }
}
