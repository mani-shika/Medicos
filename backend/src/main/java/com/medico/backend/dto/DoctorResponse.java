package com.medico.backend.dto;

public class DoctorResponse {

   private Long id;
   private String name;
   private String specialization;
   private String email;
   private String phone;
   private Boolean active;

   public DoctorResponse() {}

   public DoctorResponse(Long id, String name, String specialization,
                        String email, String phone, Boolean active) {
      this.id = id;
      this.name = name;
      this.specialization = specialization;
      this.email = email;
      this.phone = phone;
      this.active = active;
   }

    // Getters
   public Long getId() { return id; }
   public String getName() { return name; }
   public String getSpecialization() { return specialization; }
   public String getEmail() { return email; }
   public String getPhone() { return phone; }
   public Boolean getActive() { return active; }

    // Setters
   public void setId(Long id) { this.id = id; }
   public void setName(String name) { this.name = name; }
   public void setSpecialization(String specialization) { this.specialization = specialization; }
   public void setEmail(String email) { this.email = email; }
   public void setPhone(String phone) { this.phone = phone; }
   public void setActive(Boolean active) { this.active = active; }
}