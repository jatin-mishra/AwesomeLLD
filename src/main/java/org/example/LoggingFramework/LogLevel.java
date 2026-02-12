package org.example.LoggingFramework;

public enum LogLevel {
    Trace(0),
    Debug(1),
    Info(2),
    Error(3),
    Fatal(4);

    public int level;

    LogLevel(int level){
        this.level = level;
    }

    public boolean isEligible(LogLevel configured){
        return this.level >= configured.level;
    }
}
