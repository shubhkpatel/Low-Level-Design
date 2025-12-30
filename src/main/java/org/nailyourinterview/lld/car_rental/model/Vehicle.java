package org.nailyourinterview.lld.car_rental.model;

import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.car_rental.enums.*;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public abstract class Vehicle {
    private String licensePlate;
    private VehicleStatus status;
    private double pricePerHour;
    private double pricePerKm;
    private VehicleType type;
    private int bookingCount = 0;

    private final AtomicBoolean isBooked = new AtomicBoolean(false);

    public Vehicle(String licensePlate, double pricePerHour, double pricePerKm, VehicleType type) {
        this.licensePlate = licensePlate;
        this.status = VehicleStatus.AVAILABLE;
        this.pricePerHour = pricePerHour;
        this.pricePerKm = pricePerKm;
        this.type = type;
    }

    public void incrementBookingCount() {
        this.bookingCount++;
    }
}