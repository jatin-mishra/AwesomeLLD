package org.example.CricInfo.stats;

public class BatsmanStats {
    private int runs;
    private int dots;
    private int balls;
    private int sixes;
    private int fours;

    public BatsmanStats() {
        this.runs = 0;
        this.dots = 0;
        this.balls = 0;
        this.sixes = 0;
        this.fours = 0;
    }

    public void addRuns(int runs) {
        this.runs += runs;
        this.balls++;
        if (runs == 0) {
            this.dots++;
        } else if (runs == 4) {
            this.fours++;
        } else if (runs == 6) {
            this.sixes++;
        }
    }

    public int getRuns() {
        return runs;
    }

    public int getDots() {
        return dots;
    }

    public int getBalls() {
        return balls;
    }

    public int getSixes() {
        return sixes;
    }

    public int getFours() {
        return fours;
    }
}
