package org.nailyourinterview.lld.car_rental.service;

import org.nailyourinterview.lld.car_rental.enums.PaymentStatus;
import org.nailyourinterview.lld.car_rental.model.Booking;
import org.nailyourinterview.lld.car_rental.strategy.payment.PaymentStrategy;

public class PaymentProcessor {

    private final PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(Booking booking) {
        boolean success = paymentStrategy.processPayment(booking);

        if (success) {
            booking.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            System.out.println("Payment Failed!");
        }

        return success;
    }
}
