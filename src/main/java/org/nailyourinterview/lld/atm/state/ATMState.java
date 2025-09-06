package org.nailyourinterview.lld.atm.state;

import org.nailyourinterview.lld.atm.enums.ATMStatus;
import org.nailyourinterview.lld.atm.model.Card;

public interface ATMState {
    void insertCard(Card card);
    void enterPin(String pin);
    void selectOption(String option);
    void dispenseCash(int amount);
    void ejectCard();
    ATMStatus getStatus();
}