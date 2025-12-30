package org.nailyourinterview.lld.car_rental.model;

import org.nailyourinterview.lld.car_rental.enums.VehicleType;

public class Sedan extends Vehicle {
    public Sedan(String licensePlate, double pricePerHour, double pricePerKm) {
        super(licensePlate, pricePerHour, pricePerKm, VehicleType.SEDAN);
    }
}