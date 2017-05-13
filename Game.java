public class Game{
    Player player;
    int PLAYER_COUNT;
    //プレイヤーをセット
    public static Player setPlayer1(Player player){
        player = new Player(Piece.BLACK);
        return player;

    }
    public static Player setPlayer2(Player player){
        player = new Player(Piece.WHITE);
        return player;

    }
    //現在のプレイヤーを返す
    public static Player getCurrentPlayer(Player player){

        return player;
    }
    //プレイヤー交替
    public static void changeNextPlayer(){
    }
    //終了判定
    public static boolean isFinished(Board board){
            int[][] board = Board.getCellStatus();
            boolean finish = true;
            for (int i = 0; i < board.length - 4; i++) {
                for (int j = 0; j < board.length - 4; j++) {
                }
            }

            for (int i = 0; i < board.length - 4; i++) {
                for (int j = 4; j < board.length; j++) {
                }
            }
        return finish;
        }
    //勝利したプレイヤーを取得
    public static Player getWinnerPlayer(Player player){

        return player;
    }
    //新しいボードを作る
    public static Board createNewBoard(){
        return new Board();
    }
}
