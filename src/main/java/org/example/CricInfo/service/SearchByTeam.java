package org.example.CricInfo.service;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.event.Listener;
import org.example.CricInfo.event.EventData;
import org.example.CricInfo.event.MatchEventData;
import org.example.CricInfo.model.Match;

import java.util.*;

public class SearchByTeam implements SearchService, Listener {
    private Map<String, Map<String, Match>> teams;

    public SearchByTeam() {
        this.teams = new HashMap<>();
    }

    @Override
    public void onEvent(EventType eventType, EventData eventData) {
        if (eventType == EventType.MATCH_CREATED && eventData instanceof MatchEventData) {
            MatchEventData matchEventData = (MatchEventData) eventData;
            Match match = matchEventData.getMatch();

            // Add match for both teams
            String team1Name = match.getTeam1().getName();
            String team2Name = match.getTeam2().getName();

            teams.computeIfAbsent(team1Name, k -> new HashMap<>())
                 .put(match.getName(), match);
            teams.computeIfAbsent(team2Name, k -> new HashMap<>())
                 .put(match.getName(), match);
        }
    }

    @Override
    public List<Match> search(String searchValue) {
        List<Match> results = new ArrayList<>();

        if (searchValue != null && teams.containsKey(searchValue)) {
            Map<String, Match> matches = teams.get(searchValue);
            results.addAll(matches.values());
        }

        return results;
    }
}
