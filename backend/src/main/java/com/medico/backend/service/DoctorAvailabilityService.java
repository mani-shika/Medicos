package com.medico.backend.service;

import com.medico.backend.model.Doctor;
import com.medico.backend.model.DoctorAvailability;
import com.medico.backend.repository.DoctorAvailabilityRepository;
import com.medico.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAvailabilityService {

   private final DoctorAvailabilityRepository availabilityRepository;
   private final DoctorRepository doctorRepository;

   public DoctorAvailabilityService(DoctorAvailabilityRepository availabilityRepository,
                                    DoctorRepository doctorRepository) {
      this.availabilityRepository = availabilityRepository;
      this.doctorRepository = doctorRepository;
   }

    // Add availability slot
   public DoctorAvailability addAvailability(Long doctorId, String date, String time) {
        // Check if slot already exists
      boolean exists = availabilityRepository
               .existsByDoctor_IdAndDateAndTime(doctorId, date, time);

      if (exists) {
            throw new RuntimeException("Slot already exists for this doctor at " + date + " " + time);
      }

      Doctor doctor = doctorRepository.findById(doctorId)
               .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

      DoctorAvailability availability = new DoctorAvailability(doctor, date, time, true);
      return availabilityRepository.save(availability);
   }

    // Get available slots for a doctor on a date
   public List<DoctorAvailability> getAvailableSlots(Long doctorId, String date) {
      return availabilityRepository
               .findByDoctor_IdAndDateAndAvailableTrue(doctorId, date);
   }

    // Mark a slot as booked
   public void markSlotBooked(Long doctorId, String date, String time) {
      List<DoctorAvailability> slots = availabilityRepository
               .findByDoctor_IdAndDateAndAvailableTrue(doctorId, date);

      for (DoctorAvailability slot : slots) {
            if (slot.getTime().equals(time)) {
               slot.setAvailable(false);
               availabilityRepository.save(slot);
               return;
            }
         }
      throw new RuntimeException("Slot not found for " + date + " at " + time);
   }
}