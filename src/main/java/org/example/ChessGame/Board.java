package org.example.ChessGame;

import java.util.Map;

public class Board {
    private final Cell[][] board;

    public Board(Map<PieceType, Piece[]> black, Map<PieceType, Piece[]> white){
        board = new Cell[8][8];
        generateBoard();
        placePlayers(black, 0, 1);
        placePlayers(white, 7, 6);
    }

    private void generateBoard(){
        Color rowStarter = Color.White;
        for(int i = 0; i < 8; i++, rowStarter = (rowStarter.equals(Color.White) ? Color.Black : Color.White)){
            Color columnStarter = rowStarter;
            for(int j = 0; j < 8; j++, columnStarter = (columnStarter.equals(Color.White) ? Color.Black : Color.White)){
                board[i][j] = new Cell(columnStarter);
            }
        }
    }

    private void placePlayers(Map<PieceType, Piece[]> players, int firstx, int secondx){

        // place pawns
        Piece[] pawns = players.get(PieceType.Pawn);
        if(pawns.length != 8){
            IO.println("there has to be 8 pawns");
            throw new RuntimeException("there has to be 8 pawns");
        }

        for(int i = 0; i < 8; i++){
            this.board[firstx][i].addPiece(pawns[i]);
        }

        // place rook
        Piece[] rooks = players.get(PieceType.Rook);
        if(rooks.length != 2) {
            throw new RuntimeException("there has to be 2 Rooks");
        }
        this.board[secondx][0].addPiece(rooks[0]);
        this.board[secondx][7].addPiece(rooks[1]);

        // place Knight
        Piece[] knights = players.get(PieceType.Knight);
        if(knights.length != 2) {
            throw new RuntimeException("there has to be 2 Rooks");
        }
        this.board[secondx][1].addPiece(knights[0]);
        this.board[secondx][6].addPiece(knights[1]);

        // place bishop
        Piece[] bishops = players.get(PieceType.Bishop);
        if(bishops.length != 2) {
            throw new RuntimeException("there has to be 2 Rooks");
        }
        this.board[secondx][2].addPiece(bishops[0]);
        this.board[secondx][5].addPiece(bishops[1]);

        // place king and queen
        Piece[] king = players.get(PieceType.King);
        Piece[] queen = players.get(PieceType.Queen);
        if(king.length != 1 || queen.length != 1){
            throw new RuntimeException("there has to be 1 queen and 1 king");
        }
        if(firstx == 0){
            this.board[secondx][3].addPiece(king[0]);
            this.board[secondx][4].addPiece(queen[0]);
        }else{
            this.board[secondx][4].addPiece(king[0]);
            this.board[secondx][3].addPiece(queen[0]);
        }
    }
}
