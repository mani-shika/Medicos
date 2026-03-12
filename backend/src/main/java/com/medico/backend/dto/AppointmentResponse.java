package com.medico.backend.dto;

public class AppointmentResponse {

   private String message;
   private Long appointmentId;
   private String status;

   public AppointmentResponse() {}

   public AppointmentResponse(String message) {
      this.message = message;
   }

   public AppointmentResponse(String message, Long appointmentId, String status) {
      this.message = message;
      this.appointmentId = appointmentId;
      this.status = status;
   }

    // Getters
   public String getMessage() { return message; }
   public Long getAppointmentId() { return appointmentId; }
   public String getStatus() { return status; }

    // Setters
   public void setMessage(String message) { this.message = message; }
   public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
   public void setStatus(String status) { this.status = status; }
}