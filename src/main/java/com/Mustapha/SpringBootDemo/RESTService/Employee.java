package com.Mustapha.SpringBootDemo.RESTService;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
    private String name;
    private String job;
    private String description;

    public Employee(String name, String job, String description) {
        this.name = name;
        this.job = job;
        this.description = description;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("job")
    public String getJob() {
        return job;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

}