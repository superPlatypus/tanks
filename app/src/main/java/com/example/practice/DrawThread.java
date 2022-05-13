package com.example.practice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Sprite tank;
    //tank.speed = 5;
    private Sprite tank2;

    //    private Bitmap tank;
//    private int x;
//    private int y;
    private Paint paint = new Paint();
    private int angle;
    private int power;
//    private int currentAngle = 0;

    private volatile boolean running = true;//флаг для остановки потока
    private int angle_r;

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        tank = new Sprite(BitmapFactory.decodeResource(context.getResources(), R.drawable.tank1));
        tank2 = new Sprite(BitmapFactory.decodeResource(context.getResources(), R.drawable.tank1));
    }

   public void setAngAndPower(int angle, int power, int angle2, int power2) {
        tank.setAngleAndPower(angle, power);
        tank2.setAngleAndPower(angle2, power2);
    }

    public void requestStop() {
        running = false;
    }


    @Override
    public void run() {
        tank.speed = 5;
        tank2.speed = 6;
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
                    tank.update(canvas);
                    tank2.update(canvas);
                    tank.draw(canvas);
                    tank2.draw(canvas);
                    
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

