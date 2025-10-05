package org.nailyourinterview.lld.rate_limiter.factory;

import org.nailyourinterview.lld.rate_limiter.enums.RateLimitType;
import org.nailyourinterview.lld.rate_limiter.limiter.*;
import org.nailyourinterview.lld.rate_limiter.model.RateLimitConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimitType algo, RateLimitConfig config) {
        return switch (algo) {
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            case FIXED_WINDOW -> new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW_LOG -> new SlidingWindowLogRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algo);
        };
    }
}