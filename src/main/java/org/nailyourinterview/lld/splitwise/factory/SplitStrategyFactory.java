package org.nailyourinterview.lld.splitwise.factory;

import org.nailyourinterview.lld.splitwise.enums.SplitType;
import org.nailyourinterview.lld.splitwise.strategy.EqualSplitStrategy;
import org.nailyourinterview.lld.splitwise.strategy.PercentageSplitStrategy;
import org.nailyourinterview.lld.splitwise.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}