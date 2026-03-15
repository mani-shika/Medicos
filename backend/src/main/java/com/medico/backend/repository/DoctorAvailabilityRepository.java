package com.medico.backend.repository;

import com.medico.backend.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    // Check if slot already exists (prevent duplicates)
    boolean existsByDoctorIdAndDayOfWeekAndSlotTime(Long doctorId, String dayOfWeek, String slotTime);

    // Get ALL slots for a doctor
    List<DoctorAvailability> findByDoctorId(Long doctorId);

    // Get only AVAILABLE slots for a doctor
    List<DoctorAvailability> findByDoctorIdAndAvailableTrue(Long doctorId);

    // Get slots for a doctor by specific day
    List<DoctorAvailability> findByDoctorIdAndDayOfWeek(Long doctorId, String dayOfWeek);
}