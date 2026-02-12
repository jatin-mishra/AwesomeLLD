package org.example.LoggingFramework.appender;

import org.example.LoggingFramework.config.AppenderConfig;
import org.example.LoggingFramework.formatter.Formatter;
import org.example.LoggingFramework.formatter.FormatterFactory;

import java.util.Map;

public class FileAppender implements Appender {

    private Formatter formatter;

    private AppenderConfig config;

    public FileAppender(AppenderConfig config){
        this.config = config;
        this.formatter = FormatterFactory.buildFormatter(config.getLayoutConfig());
    }
    // can have different files and take lock at file level
    // every hour create new file / every day
    // keep merging files in background
    @Override
    public void append(Map<String, Object> log) {
        System.out.println("Appended to file : " + formatter.format(log));
    }

    @Override
    public AppenderType getType() {
        return config.getAppender();
    }
}
