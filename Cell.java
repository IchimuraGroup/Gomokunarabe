package com.example.c01142144b.gomokunarabe;

/**
 * Created by c01142144b on 2017/04/30.
 */

public class Cell {

    public enum STATUS{
        BLACK,
        WHITE,
        NONE
    }

    private STATUS status = STATUS.NONE;

    public void changeStatus(Piece piece){
        if (getSTATUS() != STATUS.NONE){
            return;
        }

        if (piece == Piece.BLACK){
            status = STATUS.BLACK;
        }else if (piece == Piece.WHITE){
            status = STATUS.WHITE;
        }

    }

    public STATUS getSTATUS(){
        return this.status;
    }
}
