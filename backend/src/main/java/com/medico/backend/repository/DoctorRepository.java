package com.medico.backend.repository;

import com.medico.backend.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Find doctors by specialization
    List<Doctor> findBySpecialization(String specialization);

    // Find only active doctors
    List<Doctor> findByActiveTrue();
}