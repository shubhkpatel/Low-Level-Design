package org.nailyourinterview.lld.doctors_appointment.repository;

import lombok.Getter;
import org.nailyourinterview.lld.doctors_appointment.model.Booking;

import java.util.*;

@Getter
public class BookingRepository {
    private final Map<UUID, Booking> bookingMap = new HashMap<>();
    private final Map<String, Queue<UUID>> waitlist = new HashMap<>(); // slot+doctorId -> queue of patientIds

    // id-starttime
    // 123-9:30

    public void save(Booking booking) {
        bookingMap.put(booking.getId(), booking);
    }

    public void delete(Booking booking) {
        bookingMap.remove(booking.getId());
    }

    public Booking getBookingById(UUID id){
        return bookingMap.get(id);
    }

    public List<Booking> findByDoctor(UUID doctorId) {
        List<Booking> res = new ArrayList<>();
        for (Booking b : bookingMap.values()) {
            if (b.getDoctorId().equals(doctorId)) res.add(b);
        }
        return res;
    }

    public List<Booking> findByPatient(UUID patientId) {
        List<Booking> res = new ArrayList<>();
        for (Booking b : bookingMap.values()) {
            if (b.getPatientId().equals(patientId)) res.add(b);
        }
        return res;
    }

    public void addToWaitlist(String doctorSlotKey, UUID patientId) {
        waitlist.putIfAbsent(doctorSlotKey, new LinkedList<>());
        waitlist.get(doctorSlotKey).offer(patientId);
    }

    public UUID popFromWaitlist(String doctorSlotKey) {
        Queue<UUID> queue = waitlist.get(doctorSlotKey);
        return (queue != null) ? queue.poll() : null;
    }
}