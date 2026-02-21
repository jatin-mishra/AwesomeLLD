package org.example.CricInfo.model;

import org.example.CricInfo.enums.TournamentState;
import org.example.CricInfo.enums.MatchStatus;
import org.example.CricInfo.enums.SearchType;
import org.example.CricInfo.event.EventBus;
import org.example.CricInfo.event.EventData;
import org.example.CricInfo.event.MatchEventData;
import org.example.CricInfo.enums.EventType;
import org.example.CricInfo.dto.SearchQuery;
import org.example.CricInfo.service.*;

import java.time.LocalDateTime;
import java.util.*;

public class Tournament {
    private String name;
    private TournamentState state;
    private LocalDateTime startingFrom;
    private List<Team> teams;
    private TreeMap<LocalDateTime, Match> matchesByTime;
    private Map<String, Match> matches;
    private EventBus eventBus;
    private SearchFactory searchFactory;
    private SearchByMatch searchByMatch;
    private SearchByTeam searchByTeam;
    private SearchByPlayer searchByPlayer;

    public Tournament(String name, LocalDateTime startingFrom) {
        this.name = name;
        this.state = TournamentState.OPEN_FOR_REGISTRATION;
        this.startingFrom = startingFrom;
        this.teams = new ArrayList<>();
        this.matchesByTime = new TreeMap<>();
        this.matches = new HashMap<>();
        this.eventBus = new EventBus();

        // Initialize search services
        this.searchFactory = new SearchFactory();
        this.searchByMatch = new SearchByMatch();
        this.searchByTeam = new SearchByTeam();
        this.searchByPlayer = new SearchByPlayer();

        // Register search services with factory
        searchFactory.registerSearchService(SearchType.MATCH, searchByMatch);
        searchFactory.registerSearchService(SearchType.TEAM, searchByTeam);
        searchFactory.registerSearchService(SearchType.PLAYER, searchByPlayer);

        // Register search services as event listeners
        eventBus.subscribe(EventType.MATCH_CREATED, searchByMatch);
        eventBus.subscribe(EventType.MATCH_CREATED, searchByTeam);
        eventBus.subscribe(EventType.MATCH_CREATED, searchByPlayer);
    }

    public boolean registerTeam(Team team) {
        if (state == TournamentState.OPEN_FOR_REGISTRATION) {
            teams.add(team);
            return true;
        }
        return false;
    }

    public void prepareMatchPlan() {
        // Close registration
        state = TournamentState.MATCH_PLAN_PREPARATION_STARTED;

        // Prepare plan randomly for now and sort by timeline
        // Simple round-robin for demonstration
        LocalDateTime matchTime = startingFrom;
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                String matchName = teams.get(i).getName() + " vs " + teams.get(j).getName();
                Match match = new Match(matchName, teams.get(i), teams.get(j), matchTime);
                matches.put(matchName, match);
                matchesByTime.put(matchTime, match);
                matchTime = matchTime.plusDays(1);

                // Publish MATCH_CREATED event - search services will auto-update their indices
                eventBus.publishEvent(EventType.MATCH_CREATED, new MatchEventData(EventType.MATCH_CREATED, match));
            }
        }

        // Publish event to EventBus
        eventBus.publishEvent(EventType.MATCH_PLAN_PREPARED, new EventData(EventType.MATCH_PLAN_PREPARED));
    }

    public List<Match> getUpcomingMatches() {
        LocalDateTime now = LocalDateTime.now();
        return new ArrayList<>(matchesByTime.tailMap(now).values());
    }

    public List<Match> getPastMatches() {
        LocalDateTime now = LocalDateTime.now();
        return new ArrayList<>(matchesByTime.headMap(now).values());
    }

    // needs to be improved by checking match status instead of time window
    public List<Match> getLiveMatches() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startWindow = now.minusHours(6); // Assume match duration ~6 hours
        return new ArrayList<>(matchesByTime.subMap(startWindow, now).values());
    }

    public List<Match> search(SearchQuery query) {
        Set<Match> results = null;

        // Iterate through all search criteria in the query
        for (Map.Entry<SearchType, String> entry : query.getSearchCriteria().entrySet()) {
            SearchType searchType = entry.getKey();
            String searchValue = entry.getValue();

            // Get the appropriate search service from factory
            Optional<SearchService> serviceOpt = searchFactory.getSearchService(searchType);
            if (serviceOpt.isPresent()) {
                List<Match> searchResults = serviceOpt.get().search(searchValue);
                Set<Match> matchSet = new HashSet<>(searchResults);

                // Intersect results
                if (results == null) {
                    results = matchSet;
                } else {
                    results.retainAll(matchSet);
                }
            }
        }

        return results != null ? new ArrayList<>(results) : new ArrayList<>();
    }

    public Optional<Match> getDetailsAboutMatch(String matchName) {
        return Optional.ofNullable(matches.get(matchName));
    }

    public String getName() {
        return name;
    }

    public TournamentState getState() {
        return state;
    }

    public LocalDateTime getStartingFrom() {
        return startingFrom;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public TreeMap<LocalDateTime, Match> getMatchesByTime() {
        return matchesByTime;
    }

    public Map<String, Match> getMatches() {
        return matches;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public SearchFactory getSearchFactory() {
        return searchFactory;
    }
}
