
# Problem statement
Design an online music streaming service like Spotify

# Requirements
- search for (songs, albums, artists)
- create and manage playlists
- play, pause, skip within songs

# Good to have
- user authentication and authorization
- recommend songs based on user preferences and listening history


# Out of scope but extensible to
- social sharing and offline playback


# Non Functional
- handle concurrent requests
- smooth streaming to multiple users
- handle large volumes of data (songs, users)

# Corner cases and Error Handling
- search: if no entities found? -> return empty list
- if playlist with same name already exists? -> return error message

# Entities and Relationships
SongMetadata:
- id
- title
- artist
- size
- createdAt
- likes
- status -> active/inactive


SongData:
- id
- []String <- to mimic play and stop


Album:
- title
- []Song
- creator
- likes
- status  -> active/inactive
+ addSong(song)



Artist:
- id
- name
- status -> active/inactive
- totalSongs
- followersCount


PlayList:
- LinkedHashMap<Id, Song>
- creator
- visibility (private/public)
- status -> active/inactive
+ addSong(song)
+ removeSong(id)
+ setVisibility(visibility=private/public)

SearchQuery
- AttributeType
- []{AttributeSubType, Data}


AttributeType(Enum):
- song(title, artist)
- playlist(title)
- album(title)
+ abstract isValid(subType)



CatalogService:
- SongManager
- PlayListManager
- AlbumManager

+ addSong(song)
+ addPlaylist(PlayList)
+ addAlbum(album)

+ delete...| song, playlist, album -> softdelete

+ search(SearchQuery) -> []Object (could be song, playlist, album as per attribute type)


Loader:
+ load(player, type, id)



Player: (per user level) -> every user will have one player instance only
- CatalogService
- status (nothing, play, pause)
- currentPLayingEntity
- currentPlayingEntityId
- currentSongId: songId
- preload_limit
- queue<song>

+ play()
  check state
  sync: if quyeye is empty then loader.load
  play next song
  async: if queue size reduces than threshold then load.load()
  call play()

+ pause()
  change state to paused

+ playSong(usersubscriptiontype, song)
  if subscription type is Free then add ad content
  else if subscription type is premium then play just song

+ playAlbum(albumId)
  load album and play()

+ playAlbumSong(albumId, SongId)
  load next songs and play()

+ playPlayList(playListId)
  load playlist and play()

+ playPlayListSong(playListId, songId)
  load next songs and play()


SongManager:
- Map<id, Song> songs


PlayListManager:
- Map<id, PlayList> playLists

AlbumManager:
- Map<id, Album> albums


SearchService:
- SongIndexFactory
- PlayListIndexFactory
- AlbumIndexFactory
+ search(SearchQuery)

user Factory to have precomputed instances and they share based on
attribute type and subtype

SongIndex:
+ onSongCreation(Song)


PlayListIndex:
+ onPlayListCreation(PlayList)


AlbumIndex:
+ onAlbumCreation(Album)

SongTitleIndex:
- Map<String, id> titleToId
+ search(title) -> []String

SongArtistIndex:
- Map<String, Set<Id>> artistToSongIds
+ search(artist) -> []String

same goes for PlaylistTitleIndex, and AlbumTitleIndex