package com.medico.backend.service;

import com.medico.backend.exception.ResourceNotFoundException;
import com.medico.backend.model.Appointment;
import com.medico.backend.model.Doctor;
import com.medico.backend.model.Patient;
import com.medico.backend.repository.AppointmentRepository;
import com.medico.backend.repository.DoctorRepository;
import com.medico.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    // Book appointment
    public Appointment bookAppointment(Long patientId, Long doctorId,
                                        String problem, String date, String time) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));

        Appointment appointment = new Appointment(patient, doctor, problem, date, time);
        return appointmentRepository.save(appointment);
    }

    // Get appointments by patient
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId);
    }

    // Get appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctor_Id(doctorId);
    }

    // Cancel appointment
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointmentId));
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
    }
}