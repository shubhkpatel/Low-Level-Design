package org.nailyourinterview.lld.doctors_appointment.exception;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException(String message) { super(message); }
}