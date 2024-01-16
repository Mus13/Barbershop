package com.Mustapha.SpringBootDemo.controllers;

import com.Mustapha.SpringBootDemo.responseModels.BarberResponse;
import com.Mustapha.SpringBootDemo.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/barbers")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @GetMapping("/getAll")
    public ResponseEntity<List<BarberResponse>> getAllBarbers() throws Exception{
        // Retrieve the list of barbers from the service
        List<BarberResponse> barbers = barberService.getAllBarbers();
        if (barbers.isEmpty()) {
            // If no barbers are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If barbers are found, return a 200 OK response with the list of barbers
            return ResponseEntity.ok(barbers);
        }
    }

    @GetMapping("/barber/{id}")
    public ResponseEntity<BarberResponse> getBarberById(@PathVariable Long id) throws Exception{
        // Retrieve the barber from the service
        BarberResponse barber = barberService.getBarberById(id);
        if (barber == null) {
            // If no barbers are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If barbers are found, return a 200 OK response with the list of barbers
            return ResponseEntity.ok(barber);
        }
    }

}
