package org.example.LoggingFramework.config;

import org.example.LoggingFramework.LogLevel;
import org.example.LoggingFramework.appender.AppenderType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LogConfig {
    private LogLevel globalLevel;
    private Map<String, LogLevel> loggers;
    private Map<AppenderType, AppenderConfig> appenders;

    public LogConfig(LogLevel rootLogLevel, Map<String, LogLevel> logLevelMap, Map<AppenderType, AppenderConfig> appenders){
        this.globalLevel = rootLogLevel;
        this.loggers = Optional.ofNullable(logLevelMap).orElse(new LinkedHashMap<>());
        this.appenders = Optional.ofNullable(appenders).orElse(new HashMap<>());
    }

    public LogLevel getLevel(String name){
        return loggers.entrySet().stream().filter(entry -> name.startsWith(entry.getKey()))
                .map(Map.Entry::getValue).findFirst().orElse(globalLevel);
    }

    public LogLevel getGlobalLevel() {
        return globalLevel;
    }

    public Map<String, LogLevel> getLoggers() {
        return loggers;
    }


    public Map<AppenderType, AppenderConfig> getAppenders() {
        return appenders;
    }
}
