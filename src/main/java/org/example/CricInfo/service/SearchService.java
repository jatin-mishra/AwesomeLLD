package org.example.CricInfo.service;

import org.example.CricInfo.model.Match;

import java.util.List;

public interface SearchService {
    List<Match> search(String searchValue);
}
