package com.medico.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "patient_id", nullable = false)
   private Patient patient;

   @ManyToOne
   @JoinColumn(name = "doctor_id", nullable = false)
   private Doctor doctor;

   private String problem;
   private String date;
   private String time;
   private String status;
   private LocalDateTime createdAt;

    // Constructors
   public Appointment() {
      this.createdAt = LocalDateTime.now();
      this.status = "CONFIRMED";
   }

   public Appointment(Patient patient, Doctor doctor,
                     String problem, String date, String time) {
      this.patient = patient;
      this.doctor = doctor;
      this.problem = problem;
      this.date = date;
      this.time = time;
      this.status = "CONFIRMED";
      this.createdAt = LocalDateTime.now();
   }

    // Getters
   public Long getId() { return id; }
   public Patient getPatient() { return patient; }
   public Doctor getDoctor() { return doctor; }
   public String getProblem() { return problem; }
   public String getDate() { return date; }
   public String getTime() { return time; }
   public String getStatus() { return status; }
   public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
   public void setId(Long id) { this.id = id; }
   public void setPatient(Patient patient) { this.patient = patient; }
   public void setDoctor(Doctor doctor) { this.doctor = doctor; }
   public void setProblem(String problem) { this.problem = problem; }
   public void setDate(String date) { this.date = date; }
   public void setTime(String time) { this.time = time; }
   public void setStatus(String status) { this.status = status; }
   public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}