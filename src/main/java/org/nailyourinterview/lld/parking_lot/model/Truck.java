package org.lld_yt.parking_lot.model;

import org.lld_yt.parking_lot.enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String number) { super(number, VehicleType.TRUCK); }
}
