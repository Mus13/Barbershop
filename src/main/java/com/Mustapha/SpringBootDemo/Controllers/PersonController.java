package com.Mustapha.SpringBootDemo.Controllers;


import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/clients/getAll")
    public ResponseEntity<List<PersonModel>> getAllClients() {
        // Retrieve the list of clients from the repository
        List<PersonModel> clients = personRepository.retrieveClients();
        if (clients.isEmpty()) {
            // If no clients are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If clients are found, return a 200 OK response with the list of clients
            return ResponseEntity.ok(clients);
        }
    }

    @GetMapping("/clients/getClient/{id}")
    public ResponseEntity<PersonModel> getClientById(@PathVariable long id) {
        // Retrieve the list of clients from the repository
        PersonModel client = personRepository.findClientById(id);
        if (client==null) {
            // If no client is found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(client);
        }
    }

    @PostMapping("/clients/saveClient")
    public ResponseEntity<String> saveClient(@RequestBody PersonModel person) {
        if (person != null) {
            // Save the person object using the repository
            personRepository.save(person);
            return ResponseEntity.ok("Client saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid client data");
        }
    }

    @DeleteMapping("/clients/deleteClient/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        PersonModel personModel=personRepository.findClientById(id);
        // Check if the client with the given ID exists
        if (personModel!=null) {
            personRepository.remove(personModel);
            return ResponseEntity.ok("Client with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the client doesn't exist
        }
    }

}
