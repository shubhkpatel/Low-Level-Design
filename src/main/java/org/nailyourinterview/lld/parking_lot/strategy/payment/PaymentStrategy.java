package org.lld_yt.parking_lot.strategy.payment;

import org.lld_yt.parking_lot.model.Ticket;

public interface PaymentStrategy {
    boolean processPayment(Ticket ticket, double amount);
}