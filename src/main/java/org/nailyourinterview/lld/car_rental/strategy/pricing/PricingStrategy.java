package org.nailyourinterview.lld.car_rental.strategy.pricing;

import org.nailyourinterview.lld.car_rental.model.Vehicle;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, LocalDateTime start, LocalDateTime end, double distanceKm);
}
