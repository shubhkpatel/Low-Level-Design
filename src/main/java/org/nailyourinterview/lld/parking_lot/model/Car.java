package org.nailyourinterview.lld.parking_lot.model;

import org.nailyourinterview.lld.parking_lot.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String number) { super(number, VehicleType.CAR); }
}
