package org.example.CricInfo.dto;

import org.example.CricInfo.enums.SearchType;

import java.util.HashMap;
import java.util.Map;

public class SearchQuery {
    private Map<SearchType, String> searchCriteria;

    public SearchQuery() {
        this.searchCriteria = new HashMap<>();
    }

    public SearchQuery(Map<SearchType, String> searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void addCriteria(SearchType type, String value) {
        searchCriteria.put(type, value);
    }

    public Map<SearchType, String> getSearchCriteria() {
        return searchCriteria;
    }

    public String getCriteria(SearchType type) {
        return searchCriteria.get(type);
    }
}
