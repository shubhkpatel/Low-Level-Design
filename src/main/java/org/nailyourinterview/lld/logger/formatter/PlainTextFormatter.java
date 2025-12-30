package org.nailyourinterview.lld.logger.formatter;

import org.nailyourinterview.lld.logger.model.LogMessage;

import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PlainTextFormatter implements LogFormatter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogMessage message) {
        String formattedTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(message.getTimestamp()),
                ZoneId.systemDefault()
        ).format(FORMATTER);

        return String.format("%s [%s] - %s", formattedTime, message.getLevel(), message.getMessage());
    }
}