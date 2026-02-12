package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackTraceExample {

     static void main(String[] args) {
        try {
            level1();
        } catch (Exception e) {
            String formatted = formatExceptionRootFirst(e);
            System.out.println("===== ROOT FIRST FORMAT =====");
            System.out.println(formatted);
        }
    }

    static void level1() {
        try {
            level2();
        } catch (Exception e) {
            throw new RuntimeException("Level1 failed", e);
        }
    }

    static void level2() {
        try {
            level3();
        } catch (Exception e) {
            throw new IllegalStateException("Level2 failed", e);
        }
    }

    static void level3() {
        // Root cause
        throw new NullPointerException("Actual root cause");
    }

    private static String formatExceptionRootFirst(Throwable t) {
        List<Throwable> chain = new ArrayList<>();
        while (t != null) {
            chain.add(t);
            t = t.getCause();
        }

        Collections.reverse(chain);

        StringBuilder sb = new StringBuilder();
        for (Throwable ex : chain) {
            sb.append(ex.toString()).append("\n");
            for (StackTraceElement el : ex.getStackTrace()) {
                sb.append("  at ").append(el.toString()).append("\n");
            }
        }

        return sb.toString();
    }
}