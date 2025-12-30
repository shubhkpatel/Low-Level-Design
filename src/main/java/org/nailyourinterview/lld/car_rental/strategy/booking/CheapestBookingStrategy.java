package org.nailyourinterview.lld.car_rental.strategy.booking;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.car_rental.enums.PricingStrategyType;
import org.nailyourinterview.lld.car_rental.model.Vehicle;

import java.util.List;

@AllArgsConstructor
public class CheapestBookingStrategy implements BookingStrategy {
    private final PricingStrategyType pricingType;

    @Override
    public Vehicle bookVehicle(List<Vehicle> vehicles) {
        // Sort vehicles by price based on pricingType (time or distance)
        List<Vehicle> sortedVehicles = vehicles.stream()
                .sorted((v1, v2) -> {
                    double val1 = pricingType == PricingStrategyType.TIME_BASED ? v1.getPricePerHour() : v1.getPricePerKm();
                    double val2 = pricingType == PricingStrategyType.TIME_BASED ? v2.getPricePerHour() : v2.getPricePerKm();
                    return Double.compare(val1, val2);
                })
                .toList();

        // Try booking each vehicle atomically
        for (Vehicle vehicle : sortedVehicles) {
            // t1, t2 (1th index)
            if (vehicle.getIsBooked().compareAndSet(false, true)) {
                return vehicle;
            }
        }
        return null;
    }
}
