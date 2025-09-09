package org.lld_yt.parking_lot.factory;

import org.lld_yt.parking_lot.enums.PaymentMode;
import org.lld_yt.parking_lot.strategy.payment.*;

public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}