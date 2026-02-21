package org.example.CricInfo.stats;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.enums.OutType;
import org.example.CricInfo.event.Listener;
import org.example.CricInfo.event.EventData;
import org.example.CricInfo.event.ThrowEventData;
import org.example.CricInfo.model.Player;
import org.example.CricInfo.dto.OverDetail;

import java.util.*;

public class Scorecard implements Listener {
    private Map<String, List<OverDetail>> teams;
    private Map<Player, BatsmanStats> batsmanStats;
    private Map<Player, PlayerStats> playerStats;

    public Scorecard() {
        this.teams = new HashMap<>();
        this.batsmanStats = new HashMap<>();
        this.playerStats = new HashMap<>();
    }

    @Override
    public void onEvent(EventType eventType, EventData eventData) {
        if (eventType == EventType.THROW_ADDED && eventData instanceof ThrowEventData) {
            ThrowEventData throwEventData = (ThrowEventData) eventData;

            // Add batsman stats
            Player batsman = throwEventData.getBatsmanOnStrike();
            batsmanStats.computeIfAbsent(batsman, k -> new BatsmanStats());
            batsmanStats.get(batsman).addRuns(throwEventData.getThrowResult().getRuns());

            // Update player stats for bowler
            Player bowler = throwEventData.getBowler();
            playerStats.computeIfAbsent(bowler, k -> new PlayerStats());
            PlayerStats bowlerStats = playerStats.get(bowler);
            bowlerStats.setRuns(bowlerStats.getRuns() + throwEventData.getThrowResult().getRuns());

            // Handle wicket
            if (throwEventData.getThrowResult().isOut()) {
                batsmanStats.remove(batsman);

                PlayerStats batsmanPlayerStats = playerStats.computeIfAbsent(batsman, k -> new PlayerStats());
                batsmanPlayerStats.setOutBy(bowler);
                batsmanPlayerStats.setOutMethod(throwEventData.getThrowResult().getOutType());

                bowlerStats.setWickets(bowlerStats.getWickets() + 1);

                // Handle fielder stats
                Player fielder = throwEventData.getThrowResult().getFielder();
                if (fielder != null) {
                    PlayerStats fielderStats = playerStats.computeIfAbsent(fielder, k -> new PlayerStats());
                    if (throwEventData.getThrowResult().getOutType() == OutType.CATCH) {
                        fielderStats.setCatches(fielderStats.getCatches() + 1);
                    } else if (throwEventData.getThrowResult().getOutType() == OutType.STUMPED) {
                        fielderStats.setStumpings(fielderStats.getStumpings() + 1);
                    }
                }
            }
        }
    }

    public Map<String, List<OverDetail>> getTeams() {
        return teams;
    }

    public Map<Player, BatsmanStats> getBatsmanStats() {
        return batsmanStats;
    }

    public Map<Player, PlayerStats> getPlayerStats() {
        return playerStats;
    }
}
