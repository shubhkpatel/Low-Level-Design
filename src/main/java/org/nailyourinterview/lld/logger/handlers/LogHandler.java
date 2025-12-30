package org.nailyourinterview.lld.logger.handlers;

import lombok.Setter;
import org.nailyourinterview.lld.logger.model.LogMessage;
import org.nailyourinterview.lld.logger.enums.LogLevel;
import org.nailyourinterview.lld.logger.appenders.LogAppender;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
    @Setter
    protected LogHandler next;
    protected final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    public void subscribe(LogAppender observer) {
        appenders.add(observer);
    }

     public void notifyObservers(LogMessage message) {
        for (LogAppender appender : appenders) {
            appender.append(message);
        }
    }

    public void handle(LogMessage message) {
        if (canHandle(message.getLevel())) {
            notifyObservers(message);
        }
        else if (next != null) {
            next.handle(message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}

// [console, file]

// removes the file