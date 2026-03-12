package com.medico.backend.repository;

import com.medico.backend.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    // Check if slot already exists (prevent duplicates)
        boolean existsByDoctor_IdAndDateAndTime(Long doctorId, String date, String time);

    // Get available slots for a doctor on a date
        List<DoctorAvailability> findByDoctor_IdAndDateAndAvailableTrue(Long doctorId, String date);

    // Get all slots for a doctor
        List<DoctorAvailability> findByDoctor_Id(Long doctorId);
}