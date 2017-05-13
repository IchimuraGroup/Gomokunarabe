package com.example.c01142144b.gomokunarabe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BoardView(this));

//        デバック用
//        Board board = new Board();
//        boardDebug(board);
//
//        board.put(2, 3, Piece.BLAKC);
//        board.put(4, 5, Piece.BLAKC);
//        board.put(1, 2, Piece.BLAKC);
//
//        board.put(2, 6, Piece.WHITE);
//        board.put(4, 8, Piece.WHITE);
//        board.put(0, 2, Piece.WHITE);
//
//        board.put(1, 2, Piece.WHITE);
//
//        boardDebug(board);
//
//        Log.d("canput", String.valueOf(board.canPut(1, 2)));
//        Log.d("canput", String.valueOf(board.canPut(3, 2)));

        Player player1 = new Player(Piece.BLACK);
        Player player2 = new Player(Piece.WHITE);
//        Game.setPlayer1(player1);
//        Game.setPlayer2(player2);
    }

//    デバック用
//    private void boardDebug(Board board){
//        for (int x = 0; x < Board.ROW; x++){
//            for(int y = 0; y < Board.COL; y++){
//                Log.d("x",String.valueOf(x));
//                Log.d("y",String.valueOf(y));
//                if (board.getCellSatus(x, y) == Cell.STATUS.BLACK){
//                    Log.d("status","BLACK");
//                }else if (board.getCellSatus(x, y) == Cell.STATUS.WHITE){
//                    Log.d("status","WHITE");
//                }else {
//                    Log.d("status","NONE");
//                }
//            }
//        }
//    }
}
