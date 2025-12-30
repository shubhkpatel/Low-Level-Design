package org.nailyourinterview.lld.logger.formatter;

import org.nailyourinterview.lld.logger.model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}