package org.example.CricInfo.dto;

import org.example.CricInfo.model.Player;

import java.util.*;

public class OverDetail {
    private int number;
    private Player thrower;
    private List<ThrowDetail> throwDetails;

    public OverDetail(int number, Player thrower) {
        this.number = number;
        this.thrower = thrower;
        this.throwDetails = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public Player getThrower() {
        return thrower;
    }

    public List<ThrowDetail> getThrowDetails() {
        return throwDetails;
    }

    public void addThrowDetail(ThrowDetail throwDetail) {
        if (throwDetails.size() < 6) {
            throwDetails.add(throwDetail);
        }
    }
}
