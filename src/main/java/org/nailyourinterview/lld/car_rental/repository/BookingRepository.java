package org.nailyourinterview.lld.car_rental.repository;

import org.nailyourinterview.lld.car_rental.model.Booking;

import java.util.*;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void addBooking(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public Optional<Booking> getBookingById(String bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }

    public void removeBooking(String bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking != null) {
            booking.getVehicle().getIsBooked().set(false);
        }
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
}