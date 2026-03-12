package com.medico.backend.controller;

import com.medico.backend.model.Doctor;
import com.medico.backend.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return service.getAllDoctors();
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    // Add new doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return service.addDoctor(doctor);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            Doctor doctor = service.loginDoctor(
                credentials.get("email"),
                credentials.get("password")
            );
            return ResponseEntity.ok(Map.of(
                "id", doctor.getId(),
                "name", doctor.getName(),
                "email", doctor.getEmail()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", e.getMessage()));
        }
    }

    // Delete doctor
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
    }
}