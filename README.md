Here are all the features of your MEDICOS backend:
______________________________
Doctor Management
_____________________________
Add a new doctor
View all doctors
View doctor by ID
Delete a doctor
Search doctors by specialization
Filter only active doctors

______________________
Doctor Availability
_______________________
Doctor can add available time slots
Prevents duplicate slots on same date & time
Patients can view available slots by doctor and date
Slot automatically marked as booked when appointment is made

______________________
Patient Management
______________________
Register a new patient
View all patients
View patient by ID
Update patient details
Delete a patient

__________________________
Appointment Management
___________________________
Book an appointment (links patient + doctor + date + time)
View all appointments by patient
View all appointments by doctor
Cancel an appointment
Appointment status tracking (CONFIRMED / CANCELLED)
Timestamp recorded when appointment is created

_________________
Error Handling
_________________
Clean error messages when resource not found
Handles bad requests gracefully
Global exception handler for all errors
Returns proper HTTP status codes (200, 201, 400, 404, 500)

_________________
Configuration
________________
CORS configured for frontend connection
Swagger UI for API documentation
PostgreSQL database integration
Hibernate auto-creates/updates tables


🧪 Testing

/test endpoint to verify server is running
/test/health endpoint for health check
All APIs tested via Postman
