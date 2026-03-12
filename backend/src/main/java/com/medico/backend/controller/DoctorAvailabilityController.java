package com.medico.backend.controller;

import com.medico.backend.model.DoctorAvailability;
import com.medico.backend.service.DoctorAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/availability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    public DoctorAvailabilityController(DoctorAvailabilityService service) {
        this.service = service;
    }

    // Doctor adds availability slot
    @PostMapping("/{doctorId}")
    public DoctorAvailability addAvailability(
            @PathVariable Long doctorId,
            @RequestParam String date,
            @RequestParam String time
    ) {
        return service.addAvailability(doctorId, date, time);
    }

    // Patient views available slots for a doctor
    @GetMapping("/{doctorId}")
    public List<DoctorAvailability> getAvailability(
            @PathVariable Long doctorId,
            @RequestParam String date
    ) {
        return service.getAvailableSlots(doctorId, date);
    }
}