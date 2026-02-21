package org.example.CricInfo.service;

import org.example.CricInfo.enums.SearchType;

import java.util.*;

public class SearchFactory {
    private Map<SearchType, SearchService> searchServices;

    public SearchFactory() {
        this.searchServices = new HashMap<>();
    }

    public void registerSearchService(SearchType searchType, SearchService searchService) {
        searchServices.put(searchType, searchService);
    }

    public Optional<SearchService> getSearchService(SearchType searchType) {
        return Optional.ofNullable(searchServices.get(searchType));
    }
}
