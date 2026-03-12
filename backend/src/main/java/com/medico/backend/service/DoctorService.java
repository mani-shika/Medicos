package com.medico.backend.service;

import com.medico.backend.exception.ResourceNotFoundException;
import com.medico.backend.model.Doctor;
import com.medico.backend.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(DoctorRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public Doctor addDoctor(Doctor doctor) {
        if (doctor.getPassword() != null) {
            doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        }
        return repository.save(doctor);
    }

    // Login doctor
    public Doctor loginDoctor(String email, String password) {
        List<Doctor> doctors = repository.findAll();
        Doctor found = doctors.stream()
                .filter(d -> d.getEmail() != null && d.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Doctor not found!"));

        if (!passwordEncoder.matches(password, found.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }
        return found;
    }

    public void deleteDoctor(Long id) {
        repository.deleteById(id);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return repository.findBySpecialization(specialization);
    }
}