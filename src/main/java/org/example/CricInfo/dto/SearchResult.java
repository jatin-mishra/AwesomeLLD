package org.example.CricInfo.dto;

import org.example.CricInfo.model.Match;

public class SearchResult {
    private Match match;

    public SearchResult(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }
}
