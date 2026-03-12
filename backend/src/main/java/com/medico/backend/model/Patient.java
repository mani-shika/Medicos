package com.medico.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;
   private String email;
   private String phone;
   private String address;
   private Integer age;
   private String gender;
   private String password;

    // Constructors
   public Patient() {}

   public Patient(String name, String email, String phone,
                  String address, Integer age, String gender) {
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.address = address;
      this.age = age;
      this.gender = gender;
   }

    // Getters
   public Long getId() { return id; }
   public String getName() { return name; }
   public String getEmail() { return email; }
   public String getPhone() { return phone; }
   public String getAddress() { return address; }
   public Integer getAge() { return age; }
   public String getGender() { return gender; }
   public String getPassword() { return password; }

    // Setters
   public void setId(Long id) { this.id = id; }
   public void setName(String name) { this.name = name; }
   public void setEmail(String email) { this.email = email; }
   public void setPhone(String phone) { this.phone = phone; }
   public void setAddress(String address) { this.address = address; }
   public void setAge(Integer age) { this.age = age; }
   public void setGender(String gender) { this.gender = gender; }
   public void setPassword(String password) { this.password = password; }
}