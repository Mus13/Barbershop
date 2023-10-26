package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/saveService")
    public void saveService(@RequestBody ServiceModel service) {
        serviceRepository.save(service);
    }

    @DeleteMapping("/deleteService")
    public void deleteService(@RequestBody ServiceModel service) {
        serviceRepository.delete(service);
    }
}
