# Problem
Design Cricket Information system like CrickInfo 

# Requirements (Must Have)
- information about (match, team, player, score)
- upcoming matches
- results of past matches
- search by (match, team, player)
- match details (scorecard, commentary, stats)
- real time updates of live scores and match info


# Entities and Relationships
CrickInfo
- Map<String, Tournament> tournaments
+ startTournament(Tournament) -> void

Tournament:
- name 
- state (Open for registration, Match Plan Prepared, Ongoing, Completed)
- startingFrom
- []teams
- TreeMap<MatchTime, matches>
- Map<MatchName, Match> matches
- EventBus

+ registerTeam(team) -> boolean
+ prepareMatchPlan() -> void -> event to EventBus(EventType.MATCH_PLAN_PREPARED) to update search services
  // close registration
  // prepare plan random for now and sort by timeline 
+ getUpcomingMatches() -> List<Match>
+ getPastMatches() -> List<Match>
+ getLiveMatches() -> List<Match>
+ search(SearchQuery) -> List<Match>
  // search by all non-empty and then take intersection of all results and return
+ getDetailsAboutMatch(matchName) -> Optional<Match>


SearchFactory:
- Map<SearchType, SearchService> searchServices
- registerSearchService(SearchType, SearchService) -> void
- getSearchService(SearchType) -> Optional<SearchService>

SearchService(Interface):
+ search(SearchQuery) -> List<SearchResult>

SearchByTeam extends Listener implements SearchService: (EventType: MATCH_PLAN_PREPARED)
- Map<String, Map<MatchName, Match>> teams
+ onEvent(EventType, EventData) -> void
+ search(SearchQuery) -> List<SearchResult>

Team:
- name
- []players
+ addPlayer(player) -> boolean
+ replacePlayer(oldPlayer, newPlayer) -> boolean

Player:
- name
- isRetired
- totalRuns
- totalWickets
- matchesPlayed
- []teamsPlayedFor

PlayerService:
- Map<String, Player> players
+ createPlayer({player_detail}) -> Player
+ getPlayer(playerName) -> Optional<Player>
+ retirePlayer(playerName) -> void

SearchQuery:
- matchName
- teamName
- playerName

Match:
- name 
- team1
- team2
- umpire
- thirdUmpire
- StartingAt
- status

- EventBus 

- []Commentators
- []Commentary{time, comment, commentator}
- tossWinner
- firstBatting 

- bowler
- batsmanOnStrike
- batsmanOffStrike
- over
- throwNumber

- []ThrowDetail

+ Match(name, team1, team2, startingAt) -> void
+ startMatch() -> void (prepares score-card, commentary, stats)
+ addThrow(throwDetail) -> void 
    // validate if over is complete or not
    // add throw details and send via EventBus(EventType.THROW_ADDED) to update scorecard and stats
+ addCommentary(comment, commentator) -> void
+ endMatch() -> void (finalizes score-card, commentary, stats)
+ getCurrentScene() -> {bowler, over, throwNumber, batsmanOnStrike, batsmanOffStrike} 


EventBus:
- Map<EventType, List<Listener>>
- publishEvent(EventType, EventData) -> void
  // if event type found send to all listeners subscribed to that event type
- subscribe(EventType, Listener) -> void
- unsubscribe(EventType, Listener) -> void

EventType(Enum):
- THROW_ADDED

Listener:
- onEvent(EventType, EventData) -> void

Scorecard implements Listener:
- Map<TeamName, []OverDetail> teams
- OverDetail 
- Map<BatsMan, BatsManStats{runs, dot, balls, sixes, fours}> batsmanStats
- Map<Player, {wickets, Runs, overs, catches, stumpings, bowler, out_by, out_method}> playerStats
+ onEvent(EventType, EventData, team) -> void
  // if event type is THROW_ADDED then
  // add in over detail 
  // add player stats 
  // if event type is over complete then clean over details 
  // if wicket then remove from batsmanStats and update player stats


EventData:
- eventType 

ThrowEventData extends EventData:
- bowler
- ballNumber
- overNumber
- batsmanOnStrike
- batsmanOffStrike
- throwResult {runs, isOut, outType, fielder}

OverDetail: 
- number
- thrower
- ThrowDetail -> 6 

ThrowDetail:
- batsman 
- runs
- isOut
- OutType (Wicket, Catch, LBW, RunOut, Stumped, HitWicket)
- fielder 



