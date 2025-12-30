package org.nailyourinterview.lld.car_rental.enums;

public enum VehicleStatus {
    AVAILABLE,       // Default, available to be booked if no booking conflict
    BOOKED,
    IN_SERVICE,   // Under maintenance
    DECOMMISSIONED, // Removed from fleet
}
