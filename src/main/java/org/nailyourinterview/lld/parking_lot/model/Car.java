package org.lld_yt.parking_lot.model;

import org.lld_yt.parking_lot.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String number) { super(number, VehicleType.CAR); }
}
