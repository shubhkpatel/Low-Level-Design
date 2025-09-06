package org.nailyourinterview.lld.atm.cor;

import org.nailyourinterview.lld.atm.model.ATM;

public class OneHundredDispenser implements CashDispenser {
    private CashDispenser next;

    @Override
    public void setNextDispenser(CashDispenser next) {
        this.next = next;
    }

    @Override
    public boolean canDispense(ATM atm, int amount) {
        int availableNotes = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, availableNotes);
        int remainder = amount - notes * 100;
        return remainder == 0;
    }

    @Override
    public void dispense(ATM atm, int amount) {
        int availableNotes = atm.getOneHundredCount();
        int notes = Math.min(amount / 100, availableNotes);
        atm.setOneHundredCount(availableNotes - notes);
        if(notes > 0) System.out.println("Dispensed " + notes + " x ₹100 notes");
    }
}