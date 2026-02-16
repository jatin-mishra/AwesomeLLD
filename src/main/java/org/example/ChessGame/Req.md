# Design a chess game

## Requirements (Must have)
- follow standard rules
- support two players, each controlling their own pieces
- 8x8, alternating black and white square
- make legal moves only
- detect: checkmate and stalemate condition
- handle turns and allow to make move alternatively
- support multiple game
- players accumulate scores over time
- system
  - track score for player
  - retrieve top k player
  - get player rank
  - reset player score
- handle high frequency update frequently


### Scoring method
- winner gets : score += 2 * score
- loser gets score += 1.2 * score

## Out of scope
- real UI
- agentic player (machine as player)
- undo


# Error Handling
- valid moves
  - if outside
  - else if some other player occupied then kills
- pawn moves in different direction than it kills


# Entity and Relation

## Pieces:

Pawns:
    start with first row and last rpw
    moves:
    - (2, 0) <- once
    - (1, 0)
    - (1, +1), (1, -1) <- if want to kill

rook:
    starts with corners
    moves:
    - (x += nextElement, 0)
    - (0, y += nextElement)

knight
    moves:
    - (-1/+1, +2/-2)
    - (+2/-2, -1/+1)
    starts with second to corner


bishop:
    moves
    - (-x/+x, -x/+x) -> x: until next element is found
    starts next to knight

King:
    moves
    - (-1/+1/0, -1/+1/0) except (0, 0)

Queen:
    movies
    - bishop + rook


PieceType(Enum)
- Pawn x 8
- Rook x 2
- Bishop x 2
- Knight x 2
- King x 1
- Queen x 1


Color(Enum)
- Black
- White

Piece
- type
- color

Cell
- Piece 
- color
+ addPiece(Piece)
+ getPiece() -> Optional<Piece>


Board:
- [][]Cell
+ init([]Pawns, []Rook, []Bishop, []Knight, Queen, King)
+ place(x, y, Piece) -> Optional<Piece> : killed ones
    

User
- name


Player:
- name

MoveResult
- message
- killedPiece


Game:
- Board
- []Player
- currentPlayer
- state 
- isCheckRaised

+ init(User, User)
  // creates board and two payers and assign color
+ nextPlayer() -> Player
  // if state is not started then while one otherwise alternative
+ move(user, from_x, from_y, to_x, to_y) -> MoveResult
  // check if right user
  // get the piece
  // check if valid move
  // if checkedRaised then check if after move this is handled
- checkForStaleMate()
- checkForCheckMate()
+ showState() -> prints proper game
+ getResult()
  

GameManager
    - Map<String, Game> 
    + startGame(name1, name2) -> gameId, Player{color, name}
    + move(user, from_x, from_y, to_x, to_y)
    + show(gameId)