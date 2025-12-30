package org.nailyourinterview.lld.logger.appenders;

import org.nailyourinterview.lld.logger.model.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
}