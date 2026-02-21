package org.example.CricInfo.service;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.event.EventData;
import org.example.CricInfo.event.Listener;
import org.example.CricInfo.event.MatchEventData;
import org.example.CricInfo.model.Match;
import org.example.CricInfo.model.Player;

import java.util.*;

public class SearchByPlayer implements SearchService, Listener {
    private Map<String, Set<Match>> playerMatches;

    public SearchByPlayer() {
        this.playerMatches = new HashMap<>();
    }

    @Override
    public void onEvent(EventType eventType, EventData eventData) {
        if (eventType == EventType.MATCH_CREATED && eventData instanceof MatchEventData) {
            MatchEventData matchEventData = (MatchEventData) eventData;
            Match match = matchEventData.getMatch();

            // Add match for all players in team1
            for (Player player : match.getTeam1().getPlayers()) {
                playerMatches.computeIfAbsent(player.getName(), k -> new HashSet<>())
                             .add(match);
            }

            // Add match for all players in team2
            for (Player player : match.getTeam2().getPlayers()) {
                playerMatches.computeIfAbsent(player.getName(), k -> new HashSet<>())
                             .add(match);
            }
        }
    }

    @Override
    public List<Match> search(String searchValue) {
        List<Match> results = new ArrayList<>();

        if (searchValue != null && !searchValue.isEmpty()) {
            for (Map.Entry<String, Set<Match>> entry : playerMatches.entrySet()) {
                if (entry.getKey().contains(searchValue)) {
                    results.addAll(entry.getValue());
                }
            }
        }

        return results;
    }
}
