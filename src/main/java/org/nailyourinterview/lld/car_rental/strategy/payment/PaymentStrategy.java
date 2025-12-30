package org.nailyourinterview.lld.car_rental.strategy.payment;

import org.nailyourinterview.lld.car_rental.model.Booking;

public interface PaymentStrategy {
    boolean processPayment(Booking booking);
}
