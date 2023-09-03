package com.Mustapha.SpringBootDemo.Models;

import com.Mustapha.SpringBootDemo.Security.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String Description;

    @OneToOne(mappedBy = "personModel")
    private AppUser appUser;

    @ManyToMany
    @JoinTable(
            name = "staff_services",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceModel> services = new ArrayList<>();


    public long getId() {
        return id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public void addService(ServiceModel service) {
        services.add(service);
        service.addStaff(this);
    }

    public void removeService(ServiceModel service) {
        services.remove(service);
        service.removeStaff(this);
    }
}
