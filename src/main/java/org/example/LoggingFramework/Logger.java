package org.example.LoggingFramework;

import org.example.LoggingFramework.appender.Appender;
import org.example.LoggingFramework.appender.AppenderFactory;
import org.example.LoggingFramework.config.LogConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private String name;
    private LogConfig logConfig;
    private List<Appender> appenders;

    public Logger(Class<?> clzz, LogConfig config){
        this.name = clzz.getName();
        this.logConfig = config;
        AppenderFactory.registerAppender(config.getAppenders());
        appenders = AppenderFactory.getAppenders(config.getAppenders().keySet());
    }

    public void info(String content){
        log(LogLevel.Info, content, null);
    }

    public void debug(String content){
        log(LogLevel.Debug, content, null);
    }

    public void error(String content, Throwable th){
        log(LogLevel.Error, content, th);
    }

    public void error(String content){
        log(LogLevel.Error, content, null);
    }

    private void log(LogLevel level, String content, Throwable th){
        if(!level.isEligible(this.logConfig.getLevel(this.name))) return;

        final Map<String, Object> event = new HashMap<>(Map.of(
                "level", level,
                "content", content
        ));
        if(th != null) event.put("exception", th);
        for(Appender appender : appenders){
            appender.append(event);
        }
    }
}
