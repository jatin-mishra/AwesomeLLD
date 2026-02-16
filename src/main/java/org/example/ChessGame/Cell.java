package org.example.ChessGame;

import java.util.Optional;

public class Cell {
    private Piece piece;
    private Color color;



    public Cell(Color color){
        this.color = color;
    }

    public Optional<Piece> addPiece(Piece newPiece){
        Piece oldPiece = piece;
        piece = newPiece;
        return Optional.ofNullable(oldPiece);
    }
}
