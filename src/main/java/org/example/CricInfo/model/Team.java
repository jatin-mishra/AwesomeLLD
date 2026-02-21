package org.example.CricInfo.model;

import java.util.*;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public boolean addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.getTeamsPlayedFor().add(this);
            return true;
        }
        return false;
    }

    public boolean replacePlayer(Player oldPlayer, Player newPlayer) {
        int index = players.indexOf(oldPlayer);
        if (index != -1) {
            players.set(index, newPlayer);
            newPlayer.getTeamsPlayedFor().add(this);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
