package com.example.c01142144b.gomokunarabe;

/**
 * Created by c01142144b on 2017/04/30.
 */

public class Board {
    //     public static int ROW = 9;
//     public static int COL = 9;
    public static final int SIZE = 9;
    private Cell[][] cells = new Cell[SIZE][SIZE];

    public Board(){
        for (int x = 0; x < SIZE; x++){
            for (int y = 0; y < SIZE; y++){
                cells[x][y] = new Cell();
            }
        }
    }

    public void put(int x, int y, Piece piece){
        cells[x][y].changeStatus(piece);
    }

    public boolean canPut(int x, int y){
        return cells[x][y].getSTATUS() == Cell.STATUS.NONE;
    }

    public Cell.STATUS getCellStatus(int x, int y){
        return cells[x][y].getSTATUS();
    }
}
