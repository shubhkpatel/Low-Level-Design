package org.nailyourinterview.lld.car_rental.utils;

import org.nailyourinterview.lld.car_rental.enums.BookingStatus;
import org.nailyourinterview.lld.car_rental.model.Booking;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class VehicleAvailabilityChecker {
    public static boolean isAvailable(List<Booking> bookings, LocalDateTime start, LocalDateTime end){
        List<Booking> activeBookings = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CREATED || b.getStatus() == BookingStatus.CONFIRMED)
                .sorted(Comparator.comparing(Booking::getStartTime))
                .toList();

        for (Booking b : activeBookings) {
            LocalDateTime existingStart = b.getStartTime();
            LocalDateTime existingEnd = b.getEndTime();

            // Check for overlap
            boolean overlaps = !(end.isBefore(existingStart) || start.isAfter(existingEnd));
            if (overlaps) {
                return false;
            }
        }
        return true;
    }
}