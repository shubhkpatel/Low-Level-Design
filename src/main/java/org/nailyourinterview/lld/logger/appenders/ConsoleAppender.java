package org.nailyourinterview.lld.logger.appenders;

import lombok.RequiredArgsConstructor;
import org.nailyourinterview.lld.logger.model.LogMessage;
import org.nailyourinterview.lld.logger.formatter.LogFormatter;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}