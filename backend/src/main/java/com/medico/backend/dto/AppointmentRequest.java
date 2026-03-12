package com.medico.backend.dto;

public class AppointmentRequest {

    private Long patientId;
    private Long doctorId;
    private String problem;
    private String date;
    private String time;

    // Getters
    public Long getPatientId() { return patientId; }
    public Long getDoctorId() { return doctorId; }
    public String getProblem() { return problem; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    // Setters
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public void setProblem(String problem) { this.problem = problem; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
}