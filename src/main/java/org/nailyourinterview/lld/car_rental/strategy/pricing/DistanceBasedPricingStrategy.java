package org.nailyourinterview.lld.car_rental.strategy.pricing;

import org.nailyourinterview.lld.car_rental.model.Vehicle;

import java.time.LocalDateTime;

public class DistanceBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime start, LocalDateTime end, double distanceKm) {
        return distanceKm * vehicle.getPricePerKm();
    }
}