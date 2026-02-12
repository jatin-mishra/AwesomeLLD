package org.example.LoggingFramework;

import java.util.HashMap;
import java.util.Map;

public class MDC {

    private static ThreadLocal<Map<String, String>> context = ThreadLocal.withInitial(HashMap::new);

    private MDC(){}

    public static void add(String k, String v){
        context.get().put(k, v);
    }

    public static void remove(String k){
        context.get().remove(k);
    }

    public static Map<String, String> copy(){
        return new HashMap<>(context.get());
    }

    public static void clear(){
        context.get().clear();
    }
}
