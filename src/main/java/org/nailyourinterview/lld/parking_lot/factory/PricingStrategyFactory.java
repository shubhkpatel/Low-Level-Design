package org.lld_yt.parking_lot.factory;

import org.lld_yt.parking_lot.enums.PricingStrategyType;
import org.lld_yt.parking_lot.strategy.pricing.*;

public class PricingStrategyFactory {
    public static PricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}

