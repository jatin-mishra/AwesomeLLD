package org.example.LoggingFramework.appender;


import org.example.LoggingFramework.config.AppenderConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AppenderFactory {

    private AppenderFactory(){}

    private static Map<AppenderType, Appender> appenderMap = new ConcurrentHashMap<>();

    public static void registerAppender(Map<AppenderType, AppenderConfig> appenderConfigList){
        for(var appender : appenderConfigList.entrySet()){
            appenderMap.computeIfAbsent(appender.getKey(), x -> getAppender(appender.getValue()));
        }
    }

    private static Appender getAppender(AppenderConfig appenderConfig){
        return switch (appenderConfig.getAppender()){
            case File -> new FileAppender(appenderConfig);
            default -> throw new UnsupportedOperationException("Only File appender is supported");
        };
    }


    public static List<Appender> getAppenders(Set<AppenderType> type){
        return appenderMap.values().stream().filter(appender -> type.contains(appender.getType())).toList();
    }

}
