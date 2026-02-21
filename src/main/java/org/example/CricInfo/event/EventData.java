package org.example.CricInfo.event;

import org.example.CricInfo.enums.EventType;

public class EventData {
    private EventType eventType;

    public EventData(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
