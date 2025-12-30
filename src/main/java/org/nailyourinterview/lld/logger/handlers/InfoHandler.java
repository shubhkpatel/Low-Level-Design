package org.nailyourinterview.lld.logger.handlers;

import org.nailyourinterview.lld.logger.enums.LogLevel;

public class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}