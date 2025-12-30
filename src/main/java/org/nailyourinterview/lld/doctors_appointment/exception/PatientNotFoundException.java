package org.nailyourinterview.lld.doctors_appointment.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) { super(message); }
}