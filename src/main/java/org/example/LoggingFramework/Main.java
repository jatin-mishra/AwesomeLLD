package org.example.LoggingFramework;

import org.example.LoggingFramework.appender.AppenderType;
import org.example.LoggingFramework.config.AppenderConfig;
import org.example.LoggingFramework.config.JsonLayoutConfig;
import org.example.LoggingFramework.config.LogConfig;
import org.example.LoggingFramework.formatter.Layout;

import java.util.List;
import java.util.Map;

public class Main {
    static void main() {
        LogConfig logConfig = new LogConfig(LogLevel.Error,
                Map.of("org.example.LoggingFramework", LogLevel.Debug),
                Map.of(
                        AppenderType.File,
                        new AppenderConfig(
                                AppenderType.File,
                                new JsonLayoutConfig(
                                        Layout.Json,
                                        "yyyy-MM-dd HH:mm:ss.SSS",
                                        List.of("timestamp", "level", "content"),
                                        List.of("traceId", "spanId"),
                                        true,
                                        100,
                                        false
                                )
                        )
                )
                );

        MDC.add("traceId", "tr-1");
        MDC.add("spanId", "span-2");


        Logger logger = new Logger(Main.class, logConfig);
        logger.debug("This is Jatin");
        logger.error("This is error", getThrowable());
    }

    private static Throwable getThrowable(){
        // Root cause
        NullPointerException root =
                new NullPointerException("Null value from DB");

        // Level 2
        IllegalStateException repositoryLayer =
                new IllegalStateException("Repository failure");
        repositoryLayer.initCause(root);

        // Level 1
        RuntimeException serviceLayer =
                new RuntimeException("Service failure");
        serviceLayer.initCause(repositoryLayer);

        // Top-level exception
        Exception controllerLayer =
                new Exception("Controller failure");
        controllerLayer.initCause(serviceLayer);
        return controllerLayer;
    }
}
