package com.example.c01142144b.gomokunarabe;

/**
 * Created by c01142144b on 2017/05/09.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {

    private Board board = new Board();
    private Player player = Game.getCurrentPlayer();
    private Paint paint;
    private float cx;    // タップした位置
    private float cy;    // タップした位置
    private float radius;    // 円の半径
    //     private int trout = board.COL*2;    // マスの数ｘ２
    private int troutSizeX;    //1マスのXサイズ
    private int troutSizeY;    //1マスのYサイズ
//    private Point point = new Point(0, 0);    //コンテンツ領域
    private int size;

    /**コンストラク*/
    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }
    public BoardView(Context context) {
        super(context);
        initialize();
    }

    /**PaintObject初期化*/
    private void initialize() {
        // ペイントオブジェクトを設定する
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Style.FILL);
        radius = 50;
        setPadding(50, 50, 50, 50);
        setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        size = width < height ? width  : height;
        troutSizeX = size / (Board.SIZE + 1);
        troutSizeY = size / (Board.SIZE + 1);
        setMeasuredDimension(size, size);
    }


    /**Viewのコンテンツ領域を取得*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        troutSizeX = this.getWidth() / Board.SIZE;//trout →　Board.SIZE
//        troutSizeY = this.getHeight() / Board.SIZE;//trout →　Board.SIZE
        int width = this.getWidth();
        int height = this.getHeight();
        size = width < height ? width  : height;
        troutSizeX = size / (Board.SIZE - 1);
        troutSizeY = size / (Board.SIZE - 1);
//        point.set(size, size);
    }

    /**Draw*/
    @Override
    protected void onDraw(Canvas canvas) {
        // 格子を描画する
        drawBoard(canvas);
        //Pieceを描画する
        for(int x=0;x<Board.SIZE;x++){//trout/2 → Board.SIZE
            for(int y=0;y<Board.SIZE;y++){//trout/2 → Board.SIZE
                drawPiece(canvas, x, y);
            }
        }
    }

    /**TouchEvent*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:    // 指をタッチした
                cx = event.getX(); //+ troutSizeX / 2;
                cy = event.getY(); //+ troutSizeY / 2;
//                int tx=0;    // X座標
//                int ty=0;    // Y座標
                if(cx < troutSizeX || cy < troutSizeY){
                    break;
                }
                
                if(cx > size - troutSizeX || cy > size - troutSizeY){
                    break;
                }
                //問題児　ｔｘとｔｙの値が狂う
                int tx = (int)((cx + troutSizeX * 1.5) / size * (Board.SIZE - 1));    // X座標 point.x →　size
                int ty = (int)((cy + troutSizeY * 1.5) / size * (Board.SIZE - 1));    // Y座標 point.y →　size
//                for (int i = 1; i < Board.SIZE; i++) {//i += 2 →　i++
//                    if (cx >= i * point.x / Board.SIZE && cx < (i + 2) * point.x) {//trout →　Board.SIZE
//                        tx = (i + 1) / 2;
//                    }
//                    if (cy >= i * point.y / Board.SIZE && cy < (i + 2) * point.y) {//trout →　Board.SIZE
//                        ty = (i + 1) / 2;
//                    }
//                }
                Log.i("point.x", String.valueOf(size));//point.x →　size
                Log.i("point.y", String.valueOf(size));//point.y →　size
                Log.i("tx", String.valueOf(tx));
                Log.i("ty", String.valueOf(ty));

                if (tx >= Board.SIZE || ty >= Board.SIZE){
                    break;
                }

                if (Game.isFinished(board)){
                    break;
                }

                if(board.canPut(tx,ty) == true) {
                    board.put(tx,ty,player.getPiece());
                    if (Game.isFinished(board)){
                        System.out.println(player+"の勝利");
                        break;
                    }

                    Game.changeNextPlayer();
                    player = Game.getCurrentPlayer();
                    Log.d("PlayerColor:",player.toString());
                }
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている
                assert true;    // 何もしない
                break;
            case MotionEvent.ACTION_UP:        // 指を離した
                assert true;    // 何もしない
                break;
            default:
                assert true;    // 何もしない
                break;
        }
        invalidate();
        return true;
    }

    /**格子描画*/
    private void drawBoard(Canvas canvas) {
        // 格子を描画する
        Paint paint = new Paint(Color.BLACK);
        paint.setStrokeWidth(1);
        for (int i = 1; i <= Board.SIZE; i++) {
            canvas.drawLine(troutSizeX * i, troutSizeY, troutSizeX * i, size - troutSizeY, paint);//（trout/2） →　Board.SIZE, point.x →　size
            canvas.drawLine(troutSizeX, troutSizeY * i, size - troutSizeX, troutSizeY * i, paint);//（trout/2） →　Board.SIZE, point.y →　size
        }
    }

    /**Piece描画*/
    private void drawPiece(Canvas canvas, int x,int y) {
        Paint paint = new Paint();
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth(1);
        Cell.STATUS status = board.getCellStatus(x, y);
        switch(status){
            case WHITE:
                paint.setColor(Color.WHITE);
                canvas.drawCircle(troutSizeX * x, troutSizeY * y, radius, paint);
                break;

            case BLACK:
                paint.setColor(Color.BLACK);
                canvas.drawCircle(troutSizeX * x, troutSizeY * y, radius, paint);
                break;

            case NONE:
                break;
        }
    }
}
