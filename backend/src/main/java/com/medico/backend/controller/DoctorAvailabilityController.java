package com.medico.backend.controller;

import com.medico.backend.model.DoctorAvailability;
import com.medico.backend.service.DoctorAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/availability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    public DoctorAvailabilityController(DoctorAvailabilityService service) {
        this.service = service;
    }

    // Doctor adds a slot
    // POST /availability/{doctorId}
    // Body: { "dayOfWeek": "Monday", "slotTime": "09:00" }
    @PostMapping("/{doctorId}")
    public ResponseEntity<?> addSlot(
            @PathVariable Long doctorId,
            @RequestBody Map<String, String> body) {
        try {
            DoctorAvailability slot = service.addSlot(
                    doctorId,
                    body.get("dayOfWeek"),
                    body.get("slotTime")
            );
            return ResponseEntity.ok(slot);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // Get ALL slots for a doctor (free + booked)
    // GET /availability/{doctorId}
    @GetMapping("/{doctorId}")
    public List<DoctorAvailability> getAllSlots(@PathVariable Long doctorId) {
        return service.getAllSlots(doctorId);
    }

    // Get only AVAILABLE slots for a doctor
    // GET /availability/{doctorId}/available
    @GetMapping("/{doctorId}/available")
    public List<DoctorAvailability> getAvailableSlots(@PathVariable Long doctorId) {
        return service.getAvailableSlots(doctorId);
    }

    // Get slots for a doctor on a specific day
    // GET /availability/{doctorId}/day/Monday
    @GetMapping("/{doctorId}/day/{day}")
    public List<DoctorAvailability> getSlotsByDay(
            @PathVariable Long doctorId,
            @PathVariable String day) {
        return service.getSlotsByDay(doctorId, day);
    }

    // Delete a slot
    // DELETE /availability/slot/{slotId}
    @DeleteMapping("/slot/{slotId}")
    public ResponseEntity<?> deleteSlot(@PathVariable Long slotId) {
        try {
            service.deleteSlot(slotId);
            return ResponseEntity.ok(Map.of("message", "Slot deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}