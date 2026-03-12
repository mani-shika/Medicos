package com.medico.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_availability",
      uniqueConstraints = {
            @UniqueConstraint(columnNames = {"doctor_id", "date", "time"})
      })
public class DoctorAvailability {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne
      @JoinColumn(name = "doctor_id", nullable = false)
      private Doctor doctor;

      @Column(nullable = false)
      private String date;

      @Column(nullable = false)
      private String time;

      @Column(nullable = false)
      private boolean available;

    // Constructors
      public DoctorAvailability() {}

      public DoctorAvailability(Doctor doctor, String date, String time, boolean available) {
            this.doctor = doctor;
            this.date = date;
            this.time = time;
            this.available = available;
      }

    // Getters
      public Long getId() { return id; }
      public Doctor getDoctor() { return doctor; }
      public String getDate() { return date; }
      public String getTime() { return time; }
      public boolean isAvailable() { return available; }

    // Setters
      public void setId(Long id) { this.id = id; }
      public void setDoctor(Doctor doctor) { this.doctor = doctor; }
      public void setDate(String date) { this.date = date; }
      public void setTime(String time) { this.time = time; }
      public void setAvailable(boolean available) { this.available = available; }
}