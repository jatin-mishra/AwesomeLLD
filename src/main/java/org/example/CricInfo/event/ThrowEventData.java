package org.example.CricInfo.event;

import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.model.Player;
import org.example.CricInfo.dto.ThrowResult;

public class ThrowEventData extends EventData {
    private Player bowler;
    private int ballNumber;
    private int overNumber;
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;
    private ThrowResult throwResult;

    public ThrowEventData(Player bowler, int ballNumber, int overNumber,
                          Player batsmanOnStrike, Player batsmanOffStrike,
                          ThrowResult throwResult) {
        super(EventType.THROW_ADDED);
        this.bowler = bowler;
        this.ballNumber = ballNumber;
        this.overNumber = overNumber;
        this.batsmanOnStrike = batsmanOnStrike;
        this.batsmanOffStrike = batsmanOffStrike;
        this.throwResult = throwResult;
    }

    public Player getBowler() {
        return bowler;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public Player getBatsmanOnStrike() {
        return batsmanOnStrike;
    }

    public Player getBatsmanOffStrike() {
        return batsmanOffStrike;
    }

    public ThrowResult getThrowResult() {
        return throwResult;
    }
}
