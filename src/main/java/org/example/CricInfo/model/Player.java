package org.example.CricInfo.model;

import java.util.*;

public class Player {
    private String name;
    private boolean isRetired;
    private int totalRuns;
    private int totalWickets;
    private int matchesPlayed;
    private List<Team> teamsPlayedFor;

    public Player(String name) {
        this.name = name;
        this.isRetired = false;
        this.totalRuns = 0;
        this.totalWickets = 0;
        this.matchesPlayed = 0;
        this.teamsPlayedFor = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public List<Team> getTeamsPlayedFor() {
        return teamsPlayedFor;
    }
}
