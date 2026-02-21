package org.example.CricInfo.model;

import org.example.CricInfo.enums.MatchStatus;
import org.example.CricInfo.event.EventBus;
import org.example.CricInfo.event.ThrowEventData;
import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.dto.Commentary;
import org.example.CricInfo.dto.ThrowDetail;
import org.example.CricInfo.dto.ThrowResult;
import org.example.CricInfo.dto.CurrentScene;

import java.time.LocalDateTime;
import java.util.*;

public class Match {
    private String name;
    private Team team1;
    private Team team2;
    private Player umpire;
    private Player thirdUmpire;
    private LocalDateTime startingAt;
    private MatchStatus status;

    private EventBus eventBus;

    private List<String> commentators;
    private List<Commentary> commentaries;
    private Team tossWinner;
    private Team firstBatting;

    private Player bowler;
    private Player batsmanOnStrike;
    private Player batsmanOffStrike;
    private int over;
    private int throwNumber;

    private List<ThrowDetail> throwDetails;

    public Match(String name, Team team1, Team team2, LocalDateTime startingAt) {
        this.name = name;
        this.team1 = team1;
        this.team2 = team2;
        this.startingAt = startingAt;
        this.status = MatchStatus.UPCOMING;
        this.eventBus = new EventBus();
        this.commentators = new ArrayList<>();
        this.commentaries = new ArrayList<>();
        this.throwDetails = new ArrayList<>();
        this.over = 0;
        this.throwNumber = 0;
    }

    public void startMatch() {
        this.status = MatchStatus.LIVE;
        // Prepares score-card, commentary, stats
    }

    public void addThrow(ThrowDetail throwDetail) {
        // Validate if over is complete or not
        throwDetails.add(throwDetail);
        throwNumber++;

        // Create and publish throw event
        ThrowResult throwResult = new ThrowResult(
            throwDetail.getRuns(),
            throwDetail.isOut(),
            throwDetail.getOutType(),
            throwDetail.getFielder()
        );

        ThrowEventData eventData = new ThrowEventData(
            bowler,
            throwNumber,
            over,
            batsmanOnStrike,
            batsmanOffStrike,
            throwResult
        );

        eventBus.publishEvent(EventType.THROW_ADDED, eventData);

        // Check if over is complete
        if (throwNumber == 6) {
            over++;
            throwNumber = 0;
        }
    }

    public void addCommentary(String comment, String commentator) {
        Commentary commentary = new Commentary(LocalDateTime.now(), comment, commentator);
        commentaries.add(commentary);
    }

    public void endMatch() {
        this.status = MatchStatus.COMPLETED;
        // Finalizes score-card, commentary, stats
    }

    public CurrentScene getCurrentScene() {
        return new CurrentScene(bowler, over, throwNumber, batsmanOnStrike, batsmanOffStrike);
    }

    public String getName() {
        return name;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public Player getUmpire() {
        return umpire;
    }

    public void setUmpire(Player umpire) {
        this.umpire = umpire;
    }

    public Player getThirdUmpire() {
        return thirdUmpire;
    }

    public void setThirdUmpire(Player thirdUmpire) {
        this.thirdUmpire = thirdUmpire;
    }

    public LocalDateTime getStartingAt() {
        return startingAt;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public List<String> getCommentators() {
        return commentators;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public Team getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(Team tossWinner) {
        this.tossWinner = tossWinner;
    }

    public Team getFirstBatting() {
        return firstBatting;
    }

    public void setFirstBatting(Team firstBatting) {
        this.firstBatting = firstBatting;
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getBatsmanOnStrike() {
        return batsmanOnStrike;
    }

    public void setBatsmanOnStrike(Player batsmanOnStrike) {
        this.batsmanOnStrike = batsmanOnStrike;
    }

    public Player getBatsmanOffStrike() {
        return batsmanOffStrike;
    }

    public void setBatsmanOffStrike(Player batsmanOffStrike) {
        this.batsmanOffStrike = batsmanOffStrike;
    }

    public int getOver() {
        return over;
    }

    public int getThrowNumber() {
        return throwNumber;
    }

    public List<ThrowDetail> getThrowDetails() {
        return throwDetails;
    }
}
