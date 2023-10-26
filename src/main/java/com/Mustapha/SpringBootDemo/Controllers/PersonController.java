package com.Mustapha.SpringBootDemo.Controllers;


import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Repositories.PersonRepository;
import com.Mustapha.SpringBootDemo.Security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/getAll")
    public List<PersonModel> getClients(){
        return personRepository.retrieveClients();
    }

    @PostMapping("/saveClient")
    public void saveClient(@RequestBody PersonModel person) {
        PersonModel person_model=new PersonModel("Raouf","Lhaj","Client from Algiers",new AppUser("raouf_alg","1234","Client"));
        PersonModel person_model2=new PersonModel("Anouar","Khlifa","Client from Setif",new AppUser("anouar_19","1234","Client"));
        PersonModel person_model3=new PersonModel("Mahdi","Issaoui","Client from El-Eulma",new AppUser("mahdi_eulma","1234","Client"));
        personRepository.save(person_model);
        personRepository.save(person_model2);
        personRepository.save(person_model3);
    }

    @DeleteMapping("/deleteClient")
    public void deleteClient(@RequestBody PersonModel person) {
        long id=1;
        PersonModel personModel= personRepository.findClientById(id);
        personRepository.remove(person);
    }

}
