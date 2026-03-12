package com.medico.backend.controller;

import com.medico.backend.dto.PatientRequest;
import com.medico.backend.model.Patient;
import com.medico.backend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {

   private final PatientService patientService;

   public PatientController(PatientService patientService) {
      this.patientService = patientService;
   }

    // Register
   @PostMapping
   public ResponseEntity<Patient> registerPatient(@RequestBody PatientRequest request) {
      Patient patient = patientService.registerPatient(request);
      return new ResponseEntity<>(patient, HttpStatus.CREATED);
   }

    // Login
   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
      try {
            Patient patient = patientService.loginPatient(
                  credentials.get("email"),
                  credentials.get("password")
            );
            return ResponseEntity.ok(Map.of(
                  "id", patient.getId(),
                  "name", patient.getName(),
                  "email", patient.getEmail()
            ));
      } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body(Map.of("message", e.getMessage()));
      }
   }

    // Get all patients
   @GetMapping
   public ResponseEntity<List<Patient>> getAllPatients() {
      return ResponseEntity.ok(patientService.getAllPatients());
   }

    // Get patient by ID
   @GetMapping("/{id}")
   public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
      return ResponseEntity.ok(patientService.getPatientById(id));
   }

    // Update patient
   @PutMapping("/{id}")
   public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                @RequestBody PatientRequest request) {
      return ResponseEntity.ok(patientService.updatePatient(id, request));
   }

    // Delete patient
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deletePatient(@PathVariable Long id) {
      patientService.deletePatient(id);
      return ResponseEntity.ok("Patient deleted successfully");
   }
}