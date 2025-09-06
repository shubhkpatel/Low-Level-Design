package org.nailyourinterview.lld.atm.state;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.atm.enums.ATMStatus;
import org.nailyourinterview.lld.atm.model.Card;
import org.nailyourinterview.lld.atm.service.ATMMachine;

@AllArgsConstructor
public class IdleState implements ATMState {
    private final ATMMachine atmMachine;

    @Override
    public void insertCard(Card card) {
        atmMachine.setCurrentCard(card);
        System.out.println("Card inserted.");
        atmMachine.setState(new CardInsertedState(atmMachine));
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("No card inserted.");
    }

    @Override
    public void selectOption(String option) {
        System.out.println("No card inserted.");
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("No card inserted.");
    }

    @Override
    public void ejectCard() {
        System.out.println("No card to eject.");
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.IDLE;
    }
}