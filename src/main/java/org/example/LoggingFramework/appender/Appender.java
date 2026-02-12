package org.example.LoggingFramework.appender;


import java.util.Map;

public interface Appender {
    void append(Map<String, Object> event);
    AppenderType getType();
}
