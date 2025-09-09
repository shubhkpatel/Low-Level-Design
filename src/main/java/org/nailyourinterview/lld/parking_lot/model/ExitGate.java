package org.lld_yt.parking_lot.model;

import org.lld_yt.parking_lot.enums.GateType;
import org.lld_yt.parking_lot.enums.PaymentMode;
import org.lld_yt.parking_lot.service.ParkingLot;

import java.time.LocalDateTime;

public class ExitGate extends Gate {

    public ExitGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.EXIT;
    }

    public void unparkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode) {
        ParkingLot.getInstance().unparkVehicle(ticketId, exitTime, paymentMode);
    }
}
