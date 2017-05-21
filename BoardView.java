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
    private float troutSize;        //1マスのXサイズ
    private float size;             //一片の長さ

    private final int padding=30;         //要素の中の余白
    private final float radius=30;           // 円の半径
    private final float precision=15;     //タッチ修正


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
        setBackgroundColor(Color.GREEN);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        size = width < height ? width  : height;
        setMeasuredDimension((int)size, (int)size);
        troutSize = (size-padding*2) / (Board.SIZE-1);
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
            case MotionEvent.ACTION_DOWN:

                int tx = Math.round(((event.getX()-precision)/troutSize));
                int ty = Math.round(((event.getY()-precision)/troutSize));

                if (tx >= Board.SIZE || ty >= Board.SIZE){break;}
                if (Game.isFinished(board)){break;}

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
        }
        invalidate();
        return true;
    }

    /**格子描画*/
    private void drawBoard(Canvas canvas) {
        // 格子を描画する
        Paint paint = new Paint(Color.BLACK);
        paint.setStrokeWidth(1);
        for (int i = 0; i < Board.SIZE; i++) {
            canvas.drawLine(i*troutSize+padding,padding,i*troutSize+padding,size-padding, paint);
            canvas.drawLine(padding,i*troutSize+padding,size-padding,i*troutSize+padding, paint);
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
                canvas.drawCircle(troutSize * x + padding, troutSize * y +padding, radius, paint);
                break;

            case BLACK:
                paint.setColor(Color.BLACK);
                canvas.drawCircle(troutSize * x + padding, troutSize * y + padding, radius, paint);
                break;

            case NONE:
                break;
        }
    }
}
