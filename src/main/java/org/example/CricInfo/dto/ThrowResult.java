package org.example.CricInfo.dto;

import org.example.CricInfo.enums.OutType;
import org.example.CricInfo.model.Player;

public class ThrowResult {
    private int runs;
    private boolean isOut;
    private OutType outType;
    private Player fielder;

    public ThrowResult(int runs, boolean isOut, OutType outType, Player fielder) {
        this.runs = runs;
        this.isOut = isOut;
        this.outType = outType;
        this.fielder = fielder;
    }

    public int getRuns() {
        return runs;
    }

    public boolean isOut() {
        return isOut;
    }

    public OutType getOutType() {
        return outType;
    }

    public Player getFielder() {
        return fielder;
    }
}
