package org.nailyourinterview.lld.doctors_appointment;

import org.nailyourinterview.lld.doctors_appointment.model.Booking;
import org.nailyourinterview.lld.doctors_appointment.model.Doctor;
import org.nailyourinterview.lld.doctors_appointment.dto.DoctorSlot;
import org.nailyourinterview.lld.doctors_appointment.model.Patient;
import org.nailyourinterview.lld.doctors_appointment.enums.Specialization;
import org.nailyourinterview.lld.doctors_appointment.repository.BookingRepository;
import org.nailyourinterview.lld.doctors_appointment.repository.DoctorRepository;
import org.nailyourinterview.lld.doctors_appointment.repository.PatientRepository;
import org.nailyourinterview.lld.doctors_appointment.service.BookingService;
import org.nailyourinterview.lld.doctors_appointment.service.DoctorService;
import org.nailyourinterview.lld.doctors_appointment.service.PatientService;
import org.nailyourinterview.lld.doctors_appointment.strategy.RatingBasedRankStrategy;
import org.nailyourinterview.lld.doctors_appointment.strategy.SlotRankStrategy;
import org.nailyourinterview.lld.doctors_appointment.strategy.StartTimeRankStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        BookingRepository bookingRepository = new BookingRepository();

        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        BookingService bookingService = new BookingService(bookingRepository, doctorRepository, patientRepository);
        SlotRankStrategy rankStrategy = new StartTimeRankStrategy();

        // Register doctors
        Doctor curious = doctorService.register("Curious", Specialization.CARDIOLOGIST, 4.5);
        Doctor dreadful = doctorService.register("Dreadful", Specialization.CARDIOLOGIST, 3.8);
        Doctor daring = doctorService.register("Daring", Specialization.DERMATOLOGIST, 4.2);

        // Declare availability
        doctorService.declareAvailability(curious.getId(), List.of("9:30", "12:30", "16:00"));
        doctorService.declareAvailability(dreadful.getId(), List.of("12:30", "13:00"));

        // Register patients
        Patient p1 = patientService.register("Shubh");
        Patient p2 = patientService.register("Kunal");

        // Search slots
        System.out.println("Available Cardiologist slots:");
        List<DoctorSlot> slots = bookingService.search(Specialization.CARDIOLOGIST, rankStrategy);
        for (DoctorSlot slot : slots) {
            System.out.println(slot.getDoctor().getName() + " - " + slot.getSlot());
        }

        // Book slots
        Booking b1 = bookingService.book(p1.getId(), curious.getId(), "12:30");

        // Bookings of Doctor Curious
        System.out.println("\nDoctor Curious bookings:");
        for (Booking b : bookingService.viewBookingsByDoctor(curious.getId())) {
            System.out.println("Booking: Patient ID " + patientService.findById(b.getPatientId()).getName() + ", Slot " + b.getSlot());
        }

        // Try booking same slot for another patient
        try {
            Booking b2 = bookingService.book(p2.getId(), curious.getId(), "12:30");
        } catch (Exception e) {
            System.out.println("\nPatient 2 waitlisted: " + e.getMessage());
        }

        // Cancel booking and observe waitlist trigger
        bookingService.cancel(b1.getId());

        // Final bookings
        System.out.println("\nDoctor Curious bookings:");
        for (Booking b : bookingService.viewBookingsByDoctor(curious.getId())) {
            System.out.println("Booking: Patient ID " + patientService.findById(b.getPatientId()).getName() + ", Slot " + b.getSlot());
        }
    }
}
