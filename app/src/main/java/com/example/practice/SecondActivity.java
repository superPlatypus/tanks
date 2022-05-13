package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zerokol.views.joystickView.JoystickView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Objects;

public class SecondActivity extends MainActivity{
//    private TextView angleTextView;
//    private TextView powerTextView;
    //private int angle;
    //private int power;
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



        joystick = (JoystickView) findViewById(R.id.joystickView);
        //SendTask sendTask = new SendTask();

        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                gameSurfaceView.setAngAndPowerInThread(angle,power, angle, power);
                //angle = angle1;
                //power = power1;
                new SendTask().execute(angle, power);
            }
        }, 10);


    }

    class SendTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            TextView status = (TextView) findViewById(R.id.status);
            try {
                try {
//                    clientSocket = new Socket("192.168.1.16", 4004); // этой строкой мы запрашиваем
//                    reader = new BufferedReader(new InputStreamReader(System.in));
//                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//                    status.setText("Ready!");
                    //while (true) {

                    //String word = reader.readLine();
                    MainActivity.out.write(params[0] + " " + params[1] + "\n");
                    MainActivity.out.flush();
                    String serverWord = in.readLine();
                    //status.setText(serverWord);
                    //}
                }
                finally { // в любом случае необходимо закрыть сокет и потоки
//                    status.setText("Клиент был закрыт...");
//                    clientSocket.close();
//                    in.close();
//                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }

            return null;

        }

    }

}