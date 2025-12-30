package org.nailyourinterview.lld.car_rental.factory;

import org.nailyourinterview.lld.car_rental.model.SUV;
import org.nailyourinterview.lld.car_rental.model.Sedan;
import org.nailyourinterview.lld.car_rental.enums.VehicleType;
import org.nailyourinterview.lld.car_rental.model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(VehicleType type, String licensePlate, double pricePerHour, double pricePerKm) {
        return switch (type) {
            case SEDAN -> new Sedan(licensePlate, pricePerHour, pricePerKm);
            case SUV -> new SUV(licensePlate, pricePerHour, pricePerKm);
        };
    }
}
