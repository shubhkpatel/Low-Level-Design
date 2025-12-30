package org.nailyourinterview.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.nailyourinterview.lld.splitwise.enums.SplitType;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Expense {
    private final String description;
    private final double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final SplitType splitType;
}