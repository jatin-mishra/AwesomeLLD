package org.example.CricInfo.event;

import org.example.CricInfo.enums.EventType;

public interface Listener {
    void onEvent(EventType eventType, EventData eventData);
}
