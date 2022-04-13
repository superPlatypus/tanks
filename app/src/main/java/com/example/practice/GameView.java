////                                        GameView.java is not used
//
//
//
//
//
//package com.example.practice;
//
//import static android.graphics.Bitmap.createScaledBitmap;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.os.CountDownTimer;
//import android.view.View;
//
//public class GameView extends View {
//    public GameView(Context context) {
//
//        super(context);
//        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.tank1);
//
//        int w = b.getWidth()/5;
//        int h = b.getHeight()/3;
//
//        Rect firstFrame = new Rect(0, 0, w, h);
//
//        tank = new Sprite(10, 0, 0, 100, firstFrame, b);
//
//        Timer t = new Timer();
//        t.start();
//    }
//
//    private int viewWidth;
//    private int viewHeight;
//    private Sprite tank;
//    private final int timerInterval = 30;
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        viewWidth = w;
//        viewHeight = h;
//    }
//
//    protected void update () {
//        tank.update(timerInterval);
//        invalidate();
//    }
//
//    class Timer extends CountDownTimer {
//        public Timer() {
//            super(Integer.MAX_VALUE, timerInterval);
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            update ();
//        }
//
//        @Override
//        public void onFinish() {
//        }
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas){
//        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setSubpixelText(true); // Субпиксельное сглаживание для текста
//        paint.setAntiAlias(true); // Антиальясинг, сглаживание  линий
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setColor(Color.GRAY);
//        canvas.drawPaint(paint);
//        paint.setColor(Color.GREEN);
//        tank.draw(canvas);
//
////        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, paint);
////        Bitmap tank = BitmapFactory.decodeResource(getResources(), R.drawable.tank1);
////        tank = createScaledBitmap(tank, 150, 200, true);
////        canvas.drawBitmap(tank, 0,0,paint);
//    }
//
//
//
//
//}
