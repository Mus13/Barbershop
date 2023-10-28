package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceModel>> getAllServices() {
        // Retrieve the list of clients from the repository
        List<ServiceModel> services = serviceRepository.retrieveServices();
        if (services.isEmpty()) {
            // If no services are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If services are found, return a 200 OK response with the list of clients
            return ResponseEntity.ok(services);
        }
    }

    @GetMapping("/getService/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable long id) {
        // Retrieve the list of clients from the repository
        ServiceModel service = serviceRepository.findServiceById(id);
        if (service==null) {
            // If no client is found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service);
        }
    }

    @PostMapping("/saveService")
    public ResponseEntity<String> saveService(@RequestBody ServiceModel service) {
        if (service != null) {
            // Save the person object using the repository
            serviceRepository.save(service);
            return ResponseEntity.ok("Service saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid service data");
        }
    }

    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<String> deleteService(@PathVariable long id) {
        ServiceModel serviceModel=serviceRepository.findServiceById(id);
        // Check if the client with the given ID exists
        if (serviceModel!=null) {
            serviceRepository.remove(serviceModel);
            return ResponseEntity.ok("Service with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the client doesn't exist
        }
    }
}
