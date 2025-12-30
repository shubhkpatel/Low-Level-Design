package org.nailyourinterview.lld.logger;

import org.nailyourinterview.lld.logger.appenders.LogAppender;
import org.nailyourinterview.lld.logger.enums.LogLevel;
import org.nailyourinterview.lld.logger.handlers.*;


public class LogHandlerConfiguration {

    private static final LogHandler debug = new DebugHandler();
    private static final LogHandler info = new InfoHandler();
    private static final LogHandler warn = new WarnHandler();
    private static final LogHandler error = new ErrorHandler();
    private static final LogHandler fatal = new FatalHandler();

    public static LogHandler build(){
        debug.setNext(info);
        info.setNext(warn);
        warn.setNext(error);
        error.setNext(fatal);

        return debug;
    }

    public static void addAppenderForLevel(LogLevel level, LogAppender appender) {
        switch (level) {
            case DEBUG -> debug.subscribe(appender);
            case INFO  -> info.subscribe(appender);
            case WARN  -> warn.subscribe(appender);
            case ERROR -> error.subscribe(appender);
            case FATAL -> fatal.subscribe(appender);
        }
    }
}
