package com.example.c01142144b.gomokunarabe;

/**
 * Created by c01142144b on 2017/04/30.
 */

public class Player {
    private Piece piece;
    public Player(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return this.piece;
    }
}
