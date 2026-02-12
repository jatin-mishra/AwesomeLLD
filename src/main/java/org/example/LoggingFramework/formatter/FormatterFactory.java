package org.example.LoggingFramework.formatter;

import org.example.LoggingFramework.config.JsonLayoutConfig;
import org.example.LoggingFramework.config.LayoutConfig;

public class FormatterFactory {
    public static Formatter buildFormatter(LayoutConfig config){
        return switch (config.getLayout()){
            case Json -> new JsonFormatter((JsonLayoutConfig) config);
        };
    }
}
