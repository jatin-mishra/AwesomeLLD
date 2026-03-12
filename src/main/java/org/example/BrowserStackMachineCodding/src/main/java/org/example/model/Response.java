package org.example.BrowserStackMachineCodding.src.main.java.org.example.model;

public record Response(String value) {

    public String wire() {
        return value + "\n";
    }
}