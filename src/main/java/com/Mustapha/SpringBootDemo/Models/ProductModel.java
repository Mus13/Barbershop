package com.Mustapha.SpringBootDemo.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductModel {

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String Description;
    private String instructions;
    @ManyToMany(mappedBy = "products")
    private List<ServiceModel> services = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void addService(ServiceModel service) {
        services.add(service);
    }

    public void removeService(ServiceModel service) {
        services.remove(service);
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}
