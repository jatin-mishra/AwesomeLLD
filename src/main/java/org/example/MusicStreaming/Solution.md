
MusicStreamingService:
- Map<String, Artist> 
- Player 
- Map<String, Song>
- Map<String, User>
- SearchService
- RecommendationService 
+ registerUser(user)
+ getSongRecommendations() -> []Song 
+ addArtist(artist)
+ addSong(song)
+ searchSongsByTitle(title) -> []Song

Player:
- currentIndex
- currentSong
- currentUser 
- PlayerStatus 
- PlayerState 
- []Song 
+ setCurrentSong(song)
+ setStatus(status)
+ playCurrentSongInQueue()
+ play()
+ pause()
+ next()
+ load(Playable, User)

Playable (Song, Album, Playlist)

SearchService
- searchSongByTitle([]Song, title)
- searchArtistsByName([]Artist, name)

PlayBackStrategy:
PremiumPlayBackStrategy:
FreePlayBackStrategy:

SubscriptionTier(Enum):
- FREE
- PREMIUM

RecommendationService:
GenreBasedRecommendationService: