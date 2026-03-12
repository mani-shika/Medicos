package com.medico.backend.controller;

import com.medico.backend.dto.AppointmentRequest;
import com.medico.backend.dto.AppointmentResponse;
import com.medico.backend.model.Appointment;
import com.medico.backend.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

   private final AppointmentService appointmentService;

   public AppointmentController(AppointmentService appointmentService) {
      this.appointmentService = appointmentService;
   }

    // Book appointment
   @PostMapping
   public ResponseEntity<AppointmentResponse> bookAppointment(
            @RequestBody AppointmentRequest request) {

      Appointment appointment = appointmentService.bookAppointment(
               request.getPatientId(),
               request.getDoctorId(),
               request.getProblem(),
               request.getDate(),
               request.getTime()
      );

      AppointmentResponse response = new AppointmentResponse(
               "Appointment confirmed",
               appointment.getId(),
               appointment.getStatus()
      );

      return new ResponseEntity<>(response, HttpStatus.CREATED);
   }

    // Get appointments by patient
   @GetMapping("/patient/{patientId}")
   public ResponseEntity<List<AppointmentResponse>> getPatientAppointments(
            @PathVariable Long patientId) {

      List<AppointmentResponse> responses = appointmentService
               .getAppointmentsByPatient(patientId)
               .stream()
               .map(a -> new AppointmentResponse(
                        "Appointment retrieved",
                        a.getId(),
                        a.getStatus()
               ))
               .collect(Collectors.toList());

      return ResponseEntity.ok(responses);
   }

    // Cancel appointment
   @DeleteMapping("/{appointmentId}")
   public ResponseEntity<String> cancelAppointment(
            @PathVariable Long appointmentId) {
      appointmentService.cancelAppointment(appointmentId);
      return ResponseEntity.ok("Appointment cancelled successfully");
   }

    // Get appointments by doctor
   @GetMapping("/doctor/{doctorId}")
   public ResponseEntity<List<AppointmentResponse>> getDoctorAppointments(
            @PathVariable Long doctorId) {

      List<AppointmentResponse> responses = appointmentService
               .getAppointmentsByDoctor(doctorId)
               .stream()
               .map(a -> new AppointmentResponse(
                        "Appointment retrieved",
                        a.getId(),
                        a.getStatus()
               ))
               .collect(Collectors.toList());

      return ResponseEntity.ok(responses);
   }
}