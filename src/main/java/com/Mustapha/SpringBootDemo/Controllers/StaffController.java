package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.PersonModel;
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
    @PostMapping("/saveStaff")
    public void saveStaff(@RequestBody PersonModel person) {
        staffRepository.save(person);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/deleteStaff")
    public void deleteStaff(@RequestBody PersonModel person) {
        staffRepository.delete(person);
    }

}
