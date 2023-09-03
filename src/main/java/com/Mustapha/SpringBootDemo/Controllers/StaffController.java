package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
import com.Mustapha.SpringBootDemo.Models.ServiceModel;
import com.Mustapha.SpringBootDemo.Repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/addStaff")
    public void addStaff(@RequestBody PersonModel person) {
        staffRepository.save(person);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/addService")
    public void addService(@RequestBody PersonModel person, ServiceModel serviceModel) {
        staffRepository.addService(person,serviceModel);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteService")
    public void deleteService(@RequestBody PersonModel person, ServiceModel serviceModel) {
        staffRepository.deleteService(person,serviceModel);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteStaff")
    public void deleteStaff(@RequestBody PersonModel person) {
        staffRepository.delete(person);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'STAFF')")
    @PutMapping("/updateStaff")
    public void updateStaff(@RequestBody PersonModel person) {
        staffRepository.updatePerson(person);
    }

}
