package org.nailyourinterview.lld.atm.model;

import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.atm.enums.ATMStatus;

@Getter
public class ATM {
    private final String id;
    @Setter private ATMStatus status;
    @Setter private double cashAvailable;

    @Setter private int twoThousandCount;
    @Setter private int fiveHundredCount;
    @Setter private int oneHundredCount;

    public ATM(String id, int twoThousandCount, int fiveHundredCount, int oneHundredCount) {
        this.id = id;
        this.cashAvailable = 2000 * twoThousandCount + 500 * fiveHundredCount + 100 * oneHundredCount;
        this.status = ATMStatus.IDLE;
        this.twoThousandCount = twoThousandCount;
        this.fiveHundredCount = fiveHundredCount;
        this.oneHundredCount = oneHundredCount;
    }
}
