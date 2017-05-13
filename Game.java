package com.example.c01142144b.gomokunarabe;

/**
 * Created by c01142144b on 2017/05/09.
 */
public class Game{
    //     Player player;
    private static Player player1 = new Player(Piece.BLACK);
    private static Player player2 = new Player(Piece.WHITE);
    private static Player CurrentPlayer = player1;
    //    int PLAYER_COUNT;
    //プレイヤーをセット
    public static void setPlayer1(Player player){
//         player = new Player(Piece.BLACK);
        player1 = player;
//         return player;

    }
    public static void setPlayer2(Player player){
//         player = new Player(Piece.WHITE);
        player2 = player;
//         return player;

    }
    //現在のプレイヤーを返す
    public static Player getCurrentPlayer(){
        return CurrentPlayer;
    }
    //プレイヤー交替
    public static void changeNextPlayer(){
        if(CurrentPlayer == player1){
            CurrentPlayer = player2;
        }else{
            CurrentPlayer = player1;
        }

    }
    //終了判定➔ゲームの終了
    public static boolean isFinished(Board board){
        // int[][] board = Board.getCellStatus();

//             boolean finish = false;
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                Cell.STATUS status = board.getCellStatus(x, y);
                if(status != Cell.STATUS.NONE){
//                     searchConnectCount(count, status, board, x*dir[X], y*dir[Y], dir);
                    int count1 = searchConnectCount(0, status, board, x, y, new int[]{0, 1});//上方向
                    int count2 = searchConnectCount(0, status, board, x, y, new int[]{1, 0});//右方向
                    int count3 = searchConnectCount(0, status, board, x, y, new int[]{0, -1});//下方向
                    int count4 = searchConnectCount(0, status, board, x, y, new int[]{-1, 0});//左方向

                    if(count1 >= 5 || count2 >= 5 || count3 >= 5 || count4 >= 5){
//                          finish = true;
                        return true;
                    }
                }
            }
        }

          /*  for (int i = 0; i < board.length - 4; i++) {
                for (int j = 4; j < board.length; j++) {
                }
            }*/
        return false;
    }
    //指定した座標、方向に連結している同じ種類の駒をカウントし、戻り値として返す
    private static int searchConnectCount(int count, Cell.STATUS status, Board board, int x, int y, int[] dir){
        final int X = 0;
        final int Y = 1;
        if(x < 0 || x >= Board.SIZE || y < 0 || y >= Board.SIZE){
            return count;
        }

        if(status == board.getCellStatus(x, y)){
            count++;
            count = searchConnectCount(count, status, board, x+dir[X], y+dir[Y], dir);
        }

        return count;
    }
    //新しいボードを作る
    public static Board createNewBoard(){
        return new Board();
    }
}
