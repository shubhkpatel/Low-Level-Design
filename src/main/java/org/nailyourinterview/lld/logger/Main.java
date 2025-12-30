package org.nailyourinterview.lld.logger;

import org.nailyourinterview.lld.logger.appenders.ConsoleAppender;
import org.nailyourinterview.lld.logger.appenders.FileAppender;
import org.nailyourinterview.lld.logger.enums.LogLevel;
import org.nailyourinterview.lld.logger.formatter.PlainTextFormatter;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.INFO,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new ConsoleAppender(new PlainTextFormatter())
        );

        LogHandlerConfiguration.addAppenderForLevel(
                LogLevel.ERROR,
                new FileAppender(new PlainTextFormatter(), "logs.txt")
        );

        // Usage
        logger.info("This is some key information"); // CONSOLE
        logger.error("Oh no! there's an error"); // CONSOLE + FILE
    }
}
