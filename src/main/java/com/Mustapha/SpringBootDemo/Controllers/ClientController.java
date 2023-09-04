package com.Mustapha.SpringBootDemo.Controllers;


import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/saveClient")
    public void saveClient(@RequestBody PersonModel person) {
        clientRepository.save(person);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteStaff")
    public void deleteClient(@RequestBody PersonModel person) {
        clientRepository.delete(person);
    }

}
