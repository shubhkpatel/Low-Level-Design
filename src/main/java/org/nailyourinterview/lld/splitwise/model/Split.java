package org.nailyourinterview.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Split {
    private final User user;
    @Setter
    private double amount;
}