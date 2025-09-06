package org.nailyourinterview.lld.atm.factory;

import org.nailyourinterview.lld.atm.enums.ATMStatus;
import org.nailyourinterview.lld.atm.service.ATMMachine;
import org.nailyourinterview.lld.atm.state.*;

public class ATMStateFactory {

    public static ATMState getState(ATMStatus status, ATMMachine machine) {
        return switch (status) {
            case IDLE -> new IdleState(machine);
            case CARD_INSERTED -> new CardInsertedState(machine);
            case AUTHENTICATED -> new AuthenticatedState(machine);
            case DISPENSE_CASH -> new DispenseCashState(machine);
            default -> throw new IllegalArgumentException("Unknown ATM status: " + status);
        };
    }
}
