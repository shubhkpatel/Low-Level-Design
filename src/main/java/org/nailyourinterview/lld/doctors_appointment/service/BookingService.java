package org.nailyourinterview.lld.doctors_appointment.service;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.doctors_appointment.enums.Specialization;
import org.nailyourinterview.lld.doctors_appointment.exception.BookingNotFoundException;
import org.nailyourinterview.lld.doctors_appointment.model.Booking;
import org.nailyourinterview.lld.doctors_appointment.model.Doctor;
import org.nailyourinterview.lld.doctors_appointment.dto.DoctorSlot;
import org.nailyourinterview.lld.doctors_appointment.model.Patient;
import org.nailyourinterview.lld.doctors_appointment.repository.BookingRepository;
import org.nailyourinterview.lld.doctors_appointment.repository.DoctorRepository;
import org.nailyourinterview.lld.doctors_appointment.repository.PatientRepository;
import org.nailyourinterview.lld.doctors_appointment.strategy.SlotRankStrategy;

import java.util.*;

@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public List<DoctorSlot> search(Specialization spec, SlotRankStrategy strategy) {
        List<Doctor> doctors = doctorRepo.findBySpecialization(spec);
        List<DoctorSlot> result = new ArrayList<>();

        for (Doctor d : doctors) {
            for (Map.Entry<String, Boolean> e : d.getAvailability().entrySet()) {
                if (e.getValue()) result.add(new DoctorSlot(d, e.getKey()));
            }
        }
        return strategy.rank(result);
    }

    public Booking book(UUID patientId, UUID doctorId, String slot) {
        Doctor doctor = doctorRepo.findById(doctorId);
        Map<String, Boolean> availability = doctor.getAvailability();

        // Slot not declared
        if (!availability.containsKey(slot)) {
            throw new RuntimeException("Invalid slot: Doctor has not declared availability for this slot.");
        }

        // Patient already has a booking in this slot
        for (Booking b : bookingRepo.findByPatient(patientId)) {
            if (b.getSlot().equals(slot)) {
                throw new RuntimeException("Patient already has an appointment at this time");
            }
        }

        // Book if slot is available
        if (availability.get(slot)) {
            Booking booking = new Booking(patientId, doctorId, slot);
            bookingRepo.save(booking);
            availability.put(slot, false); // mark slot as booked

            System.out.println("\n" + patientRepo.findById(patientId).getName() + " booked a slot successfully for slot : " + slot);

            return booking;
        } else {
            // Add to waitlist if valid but booked
            String key = doctorId.toString() + "-" + slot;
            bookingRepo.addToWaitlist(key, patientId);
            throw new RuntimeException("Slot already booked. Added to waitlist.");
        }
    }

    public void cancel(UUID bookingId) {
        Booking booking = bookingRepo.getBookingById(bookingId);
        if (booking == null) throw new BookingNotFoundException("Booking not found");

        Doctor doctor = doctorRepo.findById(booking.getDoctorId());
        doctor.getAvailability().put(booking.getSlot(), true); // Mark slot as available
        bookingRepo.delete(booking);

        System.out.println("\n" + patientRepo.findById(booking.getPatientId()).getName() + " cancelled the booking for slot : " + booking.getSlot());

        // Promote first patient in waitlist
        String key = doctor.getId().toString() + "-" + booking.getSlot();
        UUID nextPatient = bookingRepo.popFromWaitlist(key);
        if (nextPatient != null) {
            book(nextPatient, doctor.getId(), booking.getSlot());
        }
    }

    public List<Booking> viewBookingsByDoctor(UUID doctorId) {
        return bookingRepo.findByDoctor(doctorId);
    }

    public List<Booking> viewBookingsByPatient(UUID patientId) {
        return bookingRepo.findByPatient(patientId);
    }
}
