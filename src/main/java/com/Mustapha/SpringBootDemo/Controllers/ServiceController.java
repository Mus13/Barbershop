package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/addService")
    public void addService(@RequestBody ServiceModel service) {
        serviceRepository.save(service);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteService")
    public void deleteService(@RequestBody ServiceModel service) {
        serviceRepository.delete(service);
    }

    @PreAuthorize("hasAnyRole('OWNER')")
    @PutMapping("/updateService")
    public void updateService(@RequestBody ServiceModel service) {
        serviceRepository.updateService(service);
    }
}
