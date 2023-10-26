package com.Mustapha.SpringBootDemo.Models;

import com.Mustapha.SpringBootDemo.Security.AppUser;
import jakarta.persistence.*;

@Entity
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String description;
    @OneToOne(mappedBy = "personModel")
    private AppUser appUser;

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
