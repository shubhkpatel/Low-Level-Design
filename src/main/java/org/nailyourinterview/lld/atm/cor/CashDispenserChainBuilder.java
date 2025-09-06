package org.nailyourinterview.lld.atm.cor;

public class CashDispenserChainBuilder {
    public static CashDispenser buildChain() {
        CashDispenser d1 = new TwoThousandDispenser();
        CashDispenser d2 = new FiveHundredDispenser();
        CashDispenser d3 = new OneHundredDispenser();

        d1.setNextDispenser(d2);
        d2.setNextDispenser(d3);
        return d1;
    }
}

