package org.example.CricInfo.event;

import org.example.CricInfo.enums.EventType;

import java.util.*;

public class EventBus {
    private Map<EventType, List<Listener>> listeners;

    public EventBus() {
        this.listeners = new HashMap<>();
    }

    public void publishEvent(EventType eventType, EventData eventData) {
        if (listeners.containsKey(eventType)) {
            for (Listener listener : listeners.get(eventType)) {
                listener.onEvent(eventType, eventData);
            }
        }
    }

    public void subscribe(EventType eventType, Listener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(EventType eventType, Listener listener) {
        if (listeners.containsKey(eventType)) {
            listeners.get(eventType).remove(listener);
        }
    }
}
