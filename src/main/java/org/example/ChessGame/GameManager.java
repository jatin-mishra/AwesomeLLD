package org.example.ChessGame;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GameManager {
    private Map<String, Game> games;
    private Map<String, ReentrantReadWriteLock> locks;

    public GameManager(){
        this.games = new ConcurrentHashMap<>();
        this.locks = new ConcurrentHashMap<>();
    }

    public GameStartResponse start(String name1, String name2){
        String gameId = UUID.randomUUID().toString();
        Game game = new Game(new String[]{name1, name2});
        games.put(gameId, game);
        return new GameStartResponse(gameId, game.getPlayers());
    }

    public void move(String gameId, String player, int from_x, int from_y, int to_x, int to_y){
        var lock = locks.computeIfAbsent(gameId,x -> new ReentrantReadWriteLock()).writeLock();
        try{
            lock.lock();
            if(games.containsKey(gameId)){
                games.get(gameId).move(player, from_x, from_y, to_x, to_y);
            }
        }finally {
            lock.unlock();
        }
    }


    public void show(String gameId){
        var lock = locks.computeIfAbsent(gameId,x -> new ReentrantReadWriteLock()).readLock();
        try{
            lock.lock();
            if(games.containsKey(gameId)){
                games.get(gameId).show();
            }
        }finally {
            lock.unlock();
        }
    }
}
