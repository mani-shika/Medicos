package com.medico.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor_availability",
      uniqueConstraints = {
            @UniqueConstraint(columnNames = {"doctor_id", "day_of_week", "slot_time"})
      })
public class DoctorAvailability {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @ManyToOne
      @JoinColumn(name = "doctor_id", nullable = false)
      private Doctor doctor;

      @Column(name = "day_of_week", nullable = false)
      private String dayOfWeek;   // e.g. "Monday"

      @Column(name = "slot_time", nullable = false)
      private String slotTime;    // e.g. "09:00"

      @Column(nullable = false)
      private boolean available;

      // Constructors
      public DoctorAvailability() {}

      public DoctorAvailability(Doctor doctor, String dayOfWeek, String slotTime, boolean available) {
            this.doctor = doctor;
            this.dayOfWeek = dayOfWeek;
            this.slotTime = slotTime;
            this.available = available;
      }

      // Getters
      public Long getId() { return id; }
      public Doctor getDoctor() { return doctor; }
      public String getDayOfWeek() { return dayOfWeek; }
      public String getSlotTime() { return slotTime; }
      public boolean isAvailable() { return available; }

      // Setters
      public void setId(Long id) { this.id = id; }
      public void setDoctor(Doctor doctor) { this.doctor = doctor; }
      public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
      public void setSlotTime(String slotTime) { this.slotTime = slotTime; }
      public void setAvailable(boolean available) { this.available = available; }
}