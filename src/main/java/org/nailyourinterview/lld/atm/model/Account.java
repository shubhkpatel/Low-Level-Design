package org.nailyourinterview.lld.atm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Account {
    private final String accountNumber;
    @Setter private double balance;
}
