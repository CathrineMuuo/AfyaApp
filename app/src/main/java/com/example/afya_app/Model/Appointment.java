package com.example.afya_app.Model;
public class Appointment {
    private String appointmentDate;
    private String appointmentTime;
    private String patientName;
    private String patientNumber;

    public Appointment() {

    }

    public Appointment(String appointmentDate, String appointmentTime, String patientName, String patientNumber) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.patientName = patientName;
        this.patientNumber = patientNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientNumber() {
        return patientNumber;
    }
}