package org.example.CricInfo.model;



import java.util.*;

public class CrickInfo {
    private Map<String, Tournament> tournaments;

    public CrickInfo() {
        this.tournaments = new HashMap<>();
    }

    public void startTournament(Tournament tournament) {
        tournaments.put(tournament.getName(), tournament);
    }

    public Map<String, Tournament> getTournaments() {
        return tournaments;
    }

    public Optional<Tournament> getTournament(String tournamentName) {
        return Optional.ofNullable(tournaments.get(tournamentName));
    }
}
