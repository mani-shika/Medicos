package com.medico.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   private String specialization;
   private String email;
   private String phone;
   private Boolean active = true;
   private String password;


    // Constructors
   public Doctor() {}

   public Doctor(String name, String specialization, String email, String phone) {
      this.name = name;
      this.specialization = specialization;
      this.email = email;
      this.phone = phone;
      this.active = true;
   }

    // Getters
   public Long getId() { return id; }
   public String getName() { return name; }
   public String getSpecialization() { return specialization; }
   public String getEmail() { return email; }
   public String getPhone() { return phone; }
   public Boolean getActive() { return active; }
   public String getPassword() { return password; }

    // Setters
   public void setId(Long id) { this.id = id; }
   public void setName(String name) { this.name = name; }
   public void setSpecialization(String specialization) { this.specialization = specialization; }
   public void setEmail(String email) { this.email = email; }
   public void setPhone(String phone) { this.phone = phone; }
   public void setActive(Boolean active) { this.active = active; }
   public void setPassword(String password) { this.password = password; }
}