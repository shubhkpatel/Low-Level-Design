package org.nailyourinterview.lld.car_rental.strategy.booking;

import org.nailyourinterview.lld.car_rental.model.Vehicle;
import java.util.Comparator;
import java.util.List;

public class LeastBookedVehicleStrategy implements BookingStrategy {
    @Override
    public Vehicle bookVehicle(List<Vehicle> vehicles){
        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted(Comparator.comparingInt(Vehicle::getBookingCount))
                .toList();

        for (Vehicle vehicle : sortedVehicles) {
            // Attempt to atomically set isBooked from false to true
            if (vehicle.getIsBooked().compareAndSet(false, true)) {
                // Successfully booked
                return vehicle;
            }
            // else, this vehicle is already booked, try next
        }

        // No vehicle could be booked
        return null;
    }
}
