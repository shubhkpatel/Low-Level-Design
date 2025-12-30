package org.nailyourinterview.lld.car_rental.strategy.booking;

import org.nailyourinterview.lld.car_rental.model.Vehicle;

import java.util.List;

public interface BookingStrategy {
    Vehicle bookVehicle(List<Vehicle> vehicles);
}
