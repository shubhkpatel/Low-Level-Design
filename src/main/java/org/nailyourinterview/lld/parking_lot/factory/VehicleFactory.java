package org.lld_yt.parking_lot.factory;

import org.lld_yt.parking_lot.enums.VehicleType;
import org.lld_yt.parking_lot.model.*;

public class VehicleFactory {
    public static Vehicle create(String number, VehicleType type) {
        return switch (type) {
            case CAR -> new Car(number);
            case BIKE -> new Bike(number);
            case TRUCK -> new Truck(number);
        };
    }
}

