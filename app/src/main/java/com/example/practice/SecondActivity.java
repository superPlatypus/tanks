package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zerokol.views.joystickView.JoystickView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

public class SecondActivity extends AppCompatActivity {
//    private TextView angleTextView;
//    private TextView powerTextView;
    private JoystickView joystick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new GameSurfaceView(this));
        Objects.requireNonNull(getSupportActionBar()).hide(); //убирает title_bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        joystick = (JoystickView) findViewById(R.id.joystickView);
        LinearLayout ll = (LinearLayout) findViewById(R.id.gameLayout);
        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        ll.addView(gameSurfaceView);

        try {DatagramSocket datagramSocket = new DatagramSocket(6758);

        byte buf[] = new byte[2048];
        byte toSend[] = new byte[3];
        toSend[0] = (byte) 5.1;
        toSend[1] = 10;
        toSend[2] = 15;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        DatagramPacket send =  new DatagramPacket(toSend, toSend.length);
        System.out.println("listening..");
        while (true) {
            datagramSocket.receive(packet);
            System.out.println("recieved..");
            System.out.println("ip:" + packet.getAddress());
            System.out.println("port:" + packet.getPort());
            System.out.println(new String(packet.getData(), packet.getOffset(), packet.getLength()));
            //packet.setLength(4);
            send.setAddress(packet.getAddress());
            send.setPort(packet.getPort());
            datagramSocket.send(send);
        }}
        catch (Exception e) {
            e.printStackTrace();
        }


        joystick = (JoystickView) findViewById(R.id.joystickView);

        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                gameSurfaceView.setAngAndPowerInThread(angle,power, angle, power);

            }
        }, 10);


    }


//    Bitmap tank = BitmapFactory.decodeResource(getResources(), R.drawable.tank1);
//    int xx = canvas.getWidth(), yy = canvas.getHeight();
//    canvas.drawBitmap(image, xx - image.getWidth(), yy - image.getHeight(), paint);

}