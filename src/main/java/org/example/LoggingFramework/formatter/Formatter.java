package org.example.LoggingFramework.formatter;

import org.example.LoggingFramework.config.LayoutConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Formatter {
    String format(Map<String, Object> logData);
    String buildError(Map<String, Object> logData);
    void addMDC(Map<String, Object> logData);
}
