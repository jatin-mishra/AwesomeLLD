package org.example.CricInfo.service;

import org.example.CricInfo.model.Player;

import java.util.*;

public class PlayerService {
    private Map<String, Player> players;

    public PlayerService() {
        this.players = new HashMap<>();
    }

    public Player createPlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            Player player = new Player(playerName);
            players.put(playerName, player);
            return player;
        }
        return players.get(playerName);
    }

    public Optional<Player> getPlayer(String playerName) {
        return Optional.ofNullable(players.get(playerName));
    }

    public void retirePlayer(String playerName) {
        if (players.containsKey(playerName)) {
            players.get(playerName).setRetired(true);
        }
    }
}
