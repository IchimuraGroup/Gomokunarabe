package com.example.r.gomokunarabe;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Paint.Style;
        import android.graphics.Point;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.View;
        import android.util.Log;

public class BoardView extends View {

    Board board = new Board();
    Player player;
    private Paint paint;
    private float cx;    // タップした位置
    private float cy;    // タップした位置
    private float radius;    // 円の半径
    private int trout = 30;    // マスの数ｘ２
    private int troutSizeX;    //1マスのXサイズ
    private int troutSizeY;    //1マスのYサイズ
    Point point = new Point(0, 0);    //コンテンツ領域



    /**コンストラク*/
    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context);
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
        radius = 20;
    }

    /**Viewのコンテンツ領域を取得*/
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        point.set(this.getWidth(), this.getHeight());
        troutSizeX = this.getWidth() / trout;
        troutSizeY = this.getHeight() / trout;
    }

    /**Draw*/
    @Override
    protected void onDraw(Canvas canvas) {
        // 格子を描画する
        drawBoard(canvas);
        //Pieceを描画する
        for(int x=0;x<=trout/2;x++){
            for(int y=0;x<=trout/2;y++){
                drawPiece(canvas, board.getCellSatus(x,y),x,y);
            }
        }
     }

    /**TouchEvent*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:    // 指をタッチした
                cx = event.getX();
                cy = event.getY();
                int tx=0;    // X座標
                int ty=0;    // Y座標
                for (int i = 1; i < trout; i += 2) {
                    if (cx >= i * point.x / trout && cx < (i + 2) * point.x) {
                        tx = (i + 1) / 2;
                    }
                    if (cy >= i * point.y / trout && cy < (i + 2) * point.y) {
                        ty = (i + 1) / 2;
                    }
                }
                if(board.canPut(tx,ty)==true) {
                    board.put(tx,ty,player.getPiece());
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
    private void drawBoard(final Canvas canvas) {
        // 格子を描画する
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        for (int i = 0; i < (trout / 2); i++) {
            canvas.drawLine(point.x * i / (trout / 2), 0, point.x * i / (trout / 2), point.y, paint);
            canvas.drawLine(0, point.y * i / (trout / 2), point.x, point.y * i / (trout / 2), paint);
        }
    }

    /**Piece描画*/
    private void drawPiece(final Canvas canvas, final Cell.STATUS status,int x,int y) {
        Paint paint = new Paint();
        paint.setStyle(Style.FILL);
        paint.setStrokeWidth(1);
        switch(status){
            case WHITE:
                paint.setColor(Color.GRAY);
                canvas.drawCircle(troutSizeX * 2 * x, troutSizeY * 2 * y, radius, paint);

            case BLACK:
                paint.setColor(Color.BLACK);
                canvas.drawCircle(troutSizeX * 2 * x, troutSizeY * 2 * y, radius, paint);

            case NONE:
                break;
        }
    }
}