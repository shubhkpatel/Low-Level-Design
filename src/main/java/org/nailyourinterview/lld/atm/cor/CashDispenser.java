package org.nailyourinterview.lld.atm.cor;

import org.nailyourinterview.lld.atm.model.ATM;

public interface CashDispenser {
    void setNextDispenser(CashDispenser next);
    boolean canDispense(ATM atm, int amount);
    void dispense(ATM atm, int amount);
}
