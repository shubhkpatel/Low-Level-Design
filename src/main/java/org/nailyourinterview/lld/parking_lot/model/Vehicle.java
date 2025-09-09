package org.lld_yt.parking_lot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lld_yt.parking_lot.enums.VehicleType;

@Getter
@RequiredArgsConstructor
public abstract class Vehicle {
    private final String number;
    private final VehicleType type;
}
