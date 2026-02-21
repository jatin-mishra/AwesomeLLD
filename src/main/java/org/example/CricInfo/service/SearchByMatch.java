package org.example.CricInfo.service;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.event.EventData;
import org.example.CricInfo.event.Listener;
import org.example.CricInfo.event.MatchEventData;
import org.example.CricInfo.model.Match;

import java.util.*;

public class SearchByMatch implements SearchService, Listener {
    private Map<String, Match> matches;

    public SearchByMatch() {
        this.matches = new HashMap<>();
    }

    @Override
    public void onEvent(EventType eventType, EventData eventData) {
        if (eventType == EventType.MATCH_CREATED && eventData instanceof MatchEventData) {
            MatchEventData matchEventData = (MatchEventData) eventData;
            Match match = matchEventData.getMatch();
            matches.put(match.getName(), match);
        }
    }

    @Override
    public List<Match> search(String searchValue) {
        List<Match> results = new ArrayList<>();

        if (searchValue != null && !searchValue.isEmpty()) {
            for (Match match : matches.values()) {
                if (match.getName().contains(searchValue)) {
                    results.add(match);
                }
            }
        }

        return results;
    }
}
