package org.example.LoggingFramework.formatter;

import org.example.LoggingFramework.MDC;
import org.example.LoggingFramework.config.JsonLayoutConfig;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class JsonFormatter implements Formatter {

    private JsonLayoutConfig config;

    private SimpleDateFormat dateFormat;

    public JsonFormatter(JsonLayoutConfig config){
        this.config = config;
        this.dateFormat = new SimpleDateFormat(config.getTimestampFormat());
    }

    @Override
    public String format(Map<String, Object> logData) {
        String timestamp = dateFormat.format(new Date());
        logData.put("timestamp", timestamp);
        buildError(logData);
        addMDC(logData);
        logData = filterOut(logData, config.getIncludes());
        return formattedString(logData);
    }

    private String formattedString(Map<String, Object> data){
        // pretty
        return data.toString();
    }

    private Map<String, Object> filterOut(Map<String, Object> logData, List<String> include){
        Map<String, Object> finalMap = new HashMap<>();
        for(String k : include){
            if(logData.containsKey(k)) finalMap.put(k, logData.get(k));
        }
        return finalMap;
    }

    @Override
    public String buildError(Map<String, Object> logData) {
        Throwable throwable = (Throwable) logData.get("exception");

        if(throwable != null){
            List<Throwable> list = new ArrayList<>();
            Throwable th = throwable;
            while(th != null){
                list.add(th);
                th = th.getCause();
            }
            Collections.reverse(list);
            StringBuilder builder = new StringBuilder("");
            int line = 0;
            outer: for(Throwable t : list){
                builder.append(t.toString()).append("\n");
                line++;
                for(StackTraceElement element : t.getStackTrace()){
                    builder.append(" at ").append(element.toString()).append("\n");
                    line++;
                    if(line >= config.getDepth()){
                        builder.append("... more");
                        break outer;
                    }
                }
                if(line >= config.getDepth()){
                    builder.append("... more");
                    break outer;
                }
            }
            logData.put("error_message", builder.toString());
        }

        return "";
    }

    @Override
    public void addMDC(Map<String, Object> logData) {
        Map<String, String> context = MDC.copy();
        Map<String, Object> mdc = new HashMap<>();
        for(String key : config.getIncludeMDCKeys()){
            if(context.containsKey(key)){
                mdc.put(key.strip(), context.get(key));
            }
        }
        logData.put("mdc", mdc);
    }

}
