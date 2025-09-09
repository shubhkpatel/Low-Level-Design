package org.lld_yt.parking_lot.strategy.pricing;

import org.lld_yt.parking_lot.enums.VehicleType;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime);
}