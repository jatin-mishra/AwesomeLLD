package org.example.ChessGame;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Board board;
    private Map<PieceType, Piece[]> blackPieces;
    private Map<PieceType, Piece[]> whitePieces;
    private Player[] players;
    private Player current;
    private GameState state;

    public Game(String[] names){
        Player player1 = new Player(names[0], Color.White);
        Player player2 = new Player(names[1], Color.Black);
        this.whitePieces = new HashMap<>(generatePieces(Color.Black));
        this.blackPieces = new HashMap<>(generatePieces(Color.White));
        this.board = new Board(blackPieces, whitePieces);
        this.players = new Player[]{player1, player2};
        this.current = player1;
        this.state = GameState.Created;
    }

    public Player[] getPlayers() {
        return players;
    }

    private Map<PieceType, Piece[]> generatePieces(Color color){
        Map<PieceType, Piece[]> pieces = new HashMap<>();
        Piece[] pawns = new Piece[8];
        for(int i = 0; i < 8; i++) pawns[i] = new Piece(PieceType.Pawn, color);
        pieces.put(PieceType.Pawn, pawns);
        pieces.put(PieceType.Rook, new Piece[]{new Piece(PieceType.Rook, color), new Piece(PieceType.Rook, color)});
        pieces.put(PieceType.Knight, new Piece[]{new Piece(PieceType.Knight, color), new Piece(PieceType.Knight, color)});
        pieces.put(PieceType.Bishop, new Piece[]{new Piece(PieceType.Bishop, color), new Piece(PieceType.Bishop, color)});
        pieces.put(PieceType.King, new Piece[]{new Piece(PieceType.King, color)});
        pieces.put(PieceType.Queen, new Piece[]{new Piece(PieceType.Queen, color)});
        return pieces;
    }

    public void move(String player, int from_x, int from_y, int to_x, int to_y){
        // validate player and current player
        // get piece
        // validate piece move is valid, check for stalemate and checkmate
        // move and kill if needed
        // check for staleMate or checkmate
    }

    public boolean isStaleMate(){return true;}

    public boolean isCheckMate(){return true;}

    public void show(){

    }

}
