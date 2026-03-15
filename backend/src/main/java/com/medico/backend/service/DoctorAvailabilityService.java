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

    // Add a single slot
   public DoctorAvailability addSlot(Long doctorId, String dayOfWeek, String slotTime) {

        // Check if slot already exists
      boolean exists = availabilityRepository
               .existsByDoctorIdAndDayOfWeekAndSlotTime(doctorId, dayOfWeek, slotTime);

      if (exists) {
            throw new RuntimeException("Slot already exists for " + dayOfWeek + " at " + slotTime);
      }

      Doctor doctor = doctorRepository.findById(doctorId)
               .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

      DoctorAvailability slot = new DoctorAvailability(doctor, dayOfWeek, slotTime, true);
      return availabilityRepository.save(slot);
   }

    // Get ALL slots for a doctor (free + booked)
   public List<DoctorAvailability> getAllSlots(Long doctorId) {
      return availabilityRepository.findByDoctorId(doctorId);
   }

    // Get only AVAILABLE slots for a doctor
   public List<DoctorAvailability> getAvailableSlots(Long doctorId) {
      return availabilityRepository.findByDoctorIdAndAvailableTrue(doctorId);
   }

    // Get slots for a doctor on a specific day
   public List<DoctorAvailability> getSlotsByDay(Long doctorId, String dayOfWeek) {
      return availabilityRepository.findByDoctorIdAndDayOfWeek(doctorId, dayOfWeek);
   }

    // Delete a slot by slot ID
   public void deleteSlot(Long slotId) {
      if (!availabilityRepository.existsById(slotId)) {
            throw new RuntimeException("Slot not found with id: " + slotId);
      }
      availabilityRepository.deleteById(slotId);
   }

    // Mark slot as BOOKED
   public void markSlotBooked(Long slotId) {
      DoctorAvailability slot = availabilityRepository.findById(slotId)
               .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));
      slot.setAvailable(false);
      availabilityRepository.save(slot);
   }

    // Mark slot as AVAILABLE again (when appointment cancelled)
   public void markSlotAvailable(Long slotId) {
      DoctorAvailability slot = availabilityRepository.findById(slotId)
               .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));
      slot.setAvailable(true);
      availabilityRepository.save(slot);
   }
}