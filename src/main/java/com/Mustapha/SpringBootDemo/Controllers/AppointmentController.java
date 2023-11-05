package com.Mustapha.SpringBootDemo.Controllers;

import com.Mustapha.SpringBootDemo.Models.AppointmentModel;
import com.Mustapha.SpringBootDemo.Repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<AppointmentModel>> getAllAppointment() {
        // Retrieve the list of clients from the repository
        List<AppointmentModel> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) {
            // If no products are found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        } else {
            // If products are found, return a 200 OK response with the list of clients
            return ResponseEntity.ok(appointments);
        }
    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<AppointmentModel> getAppointmentById(@PathVariable long id){
        Optional<AppointmentModel> appointment = appointmentRepository.findById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/saveAppointment")
    public ResponseEntity<String> saveAppointment(@RequestBody AppointmentModel appointment) {
        if (appointment != null) {
            // Save the person object using the repository
            appointmentRepository.save(appointment);
            return ResponseEntity.ok("Appointment saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid appointment data");
        }
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable long id) {
        Optional<AppointmentModel> appointment=appointmentRepository.findById(id);
        // Check if the client with the given ID exists
        if (appointment.isPresent()) {
            appointmentRepository.delete(appointment.get());
            return ResponseEntity.ok("Review with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
