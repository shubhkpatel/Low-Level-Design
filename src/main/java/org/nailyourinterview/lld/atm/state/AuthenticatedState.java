package org.nailyourinterview.lld.atm.state;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.atm.enums.ATMStatus;
import org.nailyourinterview.lld.atm.model.Card;
import org.nailyourinterview.lld.atm.service.ATMMachine;

@AllArgsConstructor
public class AuthenticatedState implements ATMState {
    private final ATMMachine atmMachine;

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Already authenticated.");
    }

    @Override
    public void selectOption(String option) {
        // can add options like deposit, check balance based on option selected.
        System.out.println("Option selected: Withdrawal.");
        atmMachine.setState(new DispenseCashState(atmMachine));
    }

    @Override
    public void dispenseCash(int amount) {
        System.out.println("Select an option first.");
    }

    @Override
    public void ejectCard() {
        atmMachine.setCurrentCard(null);
        System.out.println("Card ejected.");
        atmMachine.setState(new IdleState(atmMachine));
    }

    @Override
    public ATMStatus getStatus() {
        return ATMStatus.AUTHENTICATED;
    }
}