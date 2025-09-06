package org.nailyourinterview.lld.atm.service;

import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.atm.factory.ATMStateFactory;
import org.nailyourinterview.lld.atm.model.ATM;
import org.nailyourinterview.lld.atm.model.Card;
import org.nailyourinterview.lld.atm.repository.ATMRepository;
import org.nailyourinterview.lld.atm.state.ATMState;

@Getter
public class ATMMachine {
    private final ATM atm;
    private ATMState state;
    private final ATMRepository atmRepository;
    @Setter private Card currentCard;

    public ATMMachine(String atmId, ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
        this.atm = atmRepository.getById(atmId)
                .orElseThrow(() -> new RuntimeException("ATM not found"));
        this.state = ATMStateFactory.getState(atm.getStatus(), this);
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(String option) {
        state.selectOption(option);
    }

    public void dispenseCash(int amount) {
        state.dispenseCash(amount);
    }

    public void ejectCard() {
        state.ejectCard();
    }

    public void setState(ATMState state) {
        this.state = state;
        this.atm.setStatus(state.getStatus());
        // persist the changes in db
    }
}
