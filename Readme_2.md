# MEDICOS — Healthcare Backend

## Prerequisites
- Java 21
- PostgreSQL
- Maven

## Setup

1. Clone the repo

2. Create database at postgreSQL
   CREATE DATABASE medicare_db;

3. Change application.properties
   Add your PostgreSQL password

4. Run the project
   ./mvnw spring-boot:run

5. Test at
   http://localhost:8080/test

## API Endpoints
- POST /patients → Register patient
- POST /patients/login → Patient login
- POST /doctors → Register doctor
- POST /doctors/login → Doctor login
- POST /appointments → Book appointment
- GET /appointments/patient/{id} → Get patient appointments
- GET /appointments/doctor/{id} → Get doctor appointments
- DELETE /appointments/{id} → Cancel appointment
