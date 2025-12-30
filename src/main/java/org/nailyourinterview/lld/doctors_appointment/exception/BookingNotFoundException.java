package org.nailyourinterview.lld.doctors_appointment.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) { super(message); }
}