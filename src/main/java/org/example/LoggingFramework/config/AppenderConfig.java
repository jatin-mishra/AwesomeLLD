package org.example.LoggingFramework.config;

import org.example.LoggingFramework.appender.AppenderType;

public class AppenderConfig {
    private AppenderType appender;
    private LayoutConfig layoutConfig;

    public AppenderConfig(AppenderType type, LayoutConfig config){
        this.appender = type;
        this.layoutConfig = config;
    }

    public AppenderType getAppender() {
        return appender;
    }

    public LayoutConfig getLayoutConfig() {
        return layoutConfig;
    }
}
