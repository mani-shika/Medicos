package com.medico.backend.dto;

public class PatientRequest {

   private String name;
   private String email;
   private String phone;
   private String address;
   private Integer age;
   private String gender;
   private String  password;

   public PatientRequest() {}

    // Getters
   public String getName() { return name; }
   public String getEmail() { return email; }
   public String getPhone() { return phone; }
   public String getAddress() { return address; }
   public Integer getAge() { return age; }
   public String getGender() { return gender; }
   public String getPassword() { return password; }

    // Setters
   public void setName(String name) { this.name = name; }
   public void setEmail(String email) { this.email = email; }
   public void setPhone(String phone) { this.phone = phone; }
   public void setAddress(String address) { this.address = address; }
   public void setAge(Integer age) { this.age = age; }
   public void setGender(String gender) { this.gender = gender; }
   public void setPassword(String password) { this.password = password; }
}