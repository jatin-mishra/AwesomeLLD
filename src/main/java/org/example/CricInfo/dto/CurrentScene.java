package org.example.CricInfo.dto;

import org.example.CricInfo.model.Player;

public class CurrentScene {
    private Player bowler;
    private int over;
    private int throwNumber;
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;

    public CurrentScene(Player bowler, int over, int throwNumber,
                       Player batsmanOnStrike, Player batsmanOffStrike) {
        this.bowler = bowler;
        this.over = over;
        this.throwNumber = throwNumber;
        this.batsmanOnStrike = batsmanOnStrike;
        this.batsmanOffStrike = batsmanOffStrike;
    }

    public Player getBowler() {
        return bowler;
    }

    public int getOver() {
        return over;
    }

    public int getThrowNumber() {
        return throwNumber;
    }

    public Player getBatsmanOnStrike() {
        return batsmanOnStrike;
    }

    public Player getBatsmanOffStrike() {
        return batsmanOffStrike;
    }
}
