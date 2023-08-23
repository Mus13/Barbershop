package com.Mustapha.SpringBootDemo.RESTService;

import com.Mustapha.SpringBootDemo.Models.WelcomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EmployeesController {
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private WelcomeModel welcomeModel;

    @GetMapping("/Employees")
    public Employee[] employees() {
        Employee[] employees=new Employee[2];
        employees[0]= new Employee(welcomeModel.names()[0], welcomeModel.getJobs()[0], welcomeModel.getDescriptions()[0]);
        employees[1]= new Employee(welcomeModel.names()[1], welcomeModel.getJobs()[1], welcomeModel.getDescriptions()[1]);
        return employees;
    }
}
