package org.example.CricInfo.stats;

import org.example.CricInfo.enums.OutType;
import org.example.CricInfo.model.Player;

public class PlayerStats {
    private int wickets;
    private int runs;
    private int overs;
    private int catches;
    private int stumpings;
    private Player bowler;
    private Player outBy;
    private OutType outMethod;

    public PlayerStats() {
        this.wickets = 0;
        this.runs = 0;
        this.overs = 0;
        this.catches = 0;
        this.stumpings = 0;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getCatches() {
        return catches;
    }

    public void setCatches(int catches) {
        this.catches = catches;
    }

    public int getStumpings() {
        return stumpings;
    }

    public void setStumpings(int stumpings) {
        this.stumpings = stumpings;
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getOutBy() {
        return outBy;
    }

    public void setOutBy(Player outBy) {
        this.outBy = outBy;
    }

    public OutType getOutMethod() {
        return outMethod;
    }

    public void setOutMethod(OutType outMethod) {
        this.outMethod = outMethod;
    }
}
