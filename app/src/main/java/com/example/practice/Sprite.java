package com.example.practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Bitmap bitmap;
    private double frameTime;
    private double timeForCurrentFrame;

    private double bitmapX = 50;
    private double bitmapY = 50;
    private int angle_m; // angle for moving
    private int angle_r; // angle for rotating
    private int power;
    private double currSpeed;

    //final private double speed = 5;
    public double speed;
    final private int padding = 20;

    public Sprite(Bitmap bitmap){
       this.bitmap = Bitmap.createScaledBitmap(bitmap, 150, 200, true);
        //bitmapY = bitmap.getHeight()/2;
    }

    public void setAngleAndPower(int angle, int power){
        angle_m = angle - 90;
        angle_r = angle ;
        this.power = power;
    }

    public void update(Canvas canvas){
        double x = Math.cos(Math.toRadians(angle_m));
        double y = Math.sin(Math.toRadians(angle_m));
        double length = Math.sqrt((x * x) + (y * y));
        x /= length;
        y /= length;
        currSpeed = speed * (power / 100.0f);
        x *= currSpeed;
        y *= currSpeed;
        bitmapX += x;
        bitmapY += y;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        //canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
        canvas.rotate(angle_r, (float)bitmapX + bitmap.getWidth()/2f, (float)bitmapY + bitmap.getHeight()/2f);
        canvas.drawBitmap(bitmap, (float)bitmapX, (float)bitmapY, paint);
        canvas.rotate(-angle_r, (float)bitmapX + bitmap.getWidth()/2f, (float)bitmapY + bitmap.getHeight()/2f);
    }

    public Rect getBoundingBoxRect () {
        return new Rect((int)bitmapX+padding, (int)bitmapY+padding, (int)(bitmapX + bitmap.getWidth() - 2 *padding),
                (int)(bitmapY + bitmap.getHeight() - 2* padding));
    }

    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
}
