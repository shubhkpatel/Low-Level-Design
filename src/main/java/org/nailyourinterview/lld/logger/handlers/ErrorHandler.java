package org.nailyourinterview.lld.logger.handlers;

import org.nailyourinterview.lld.logger.enums.LogLevel;

public class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }
}