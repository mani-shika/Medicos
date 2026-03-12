package com.medico.backend.service;

import com.medico.backend.dto.PatientRequest;
import com.medico.backend.exception.ResourceNotFoundException;
import com.medico.backend.model.Patient;
import com.medico.backend.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

   private final PatientRepository repository;
   final PasswordEncoder passwordEncoder;

   public PatientService(PatientRepository repository, PasswordEncoder passwordEncoder) {
      this.repository = repository;
      this.passwordEncoder = passwordEncoder;
   }

    // Register new patient
   public Patient registerPatient(PatientRequest request) {
        // Check if email already exists
      if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered!");
      }
      Patient patient = new Patient(
               request.getName(),
               request.getEmail(),
               request.getPhone(),
               request.getAddress(),
               request.getAge(),
               request.getGender()
      );
        // Encrypt password before saving
      patient.setPassword(passwordEncoder.encode(request.getPassword()));
      return repository.save(patient);
   }

    // Login patient
   public Patient loginPatient(String email, String password) {
      Patient patient = repository.findByEmail(email)
               .orElseThrow(() -> new RuntimeException("Patient not found!"));

      if (!passwordEncoder.matches(password, patient.getPassword())) {
            throw new RuntimeException("Invalid password!");
      }
      return patient;
   }

    // Get all patients
   public List<Patient> getAllPatients() {
      return repository.findAll();
   }

    // Get patient by ID
   public Patient getPatientById(Long id) {
      return repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
   }

    // Update patient
   public Patient updatePatient(Long id, PatientRequest request) {
      Patient patient = getPatientById(id);
      patient.setName(request.getName());
      patient.setEmail(request.getEmail());
      patient.setPhone(request.getPhone());
      patient.setAddress(request.getAddress());
      patient.setAge(request.getAge());
      patient.setGender(request.getGender());
      return repository.save(patient);
   }

    // Delete patient
   public void deletePatient(Long id) {
      repository.deleteById(id);
   }
}