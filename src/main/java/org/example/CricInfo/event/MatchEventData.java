package org.example.CricInfo.event;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.model.Match;

public class MatchEventData extends EventData {
    private Match match;

    public MatchEventData(EventType eventType, Match match) {
        super(eventType);
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }
}
