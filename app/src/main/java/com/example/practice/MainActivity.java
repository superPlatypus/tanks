package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zerokol.views.joystickView.JoystickView;
import com.zerokol.views.joystickView.JoystickView.OnJoystickMoveListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {
    public int config;
    public DatagramPacket datagramPacket;
    public DatagramSocket datagramSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });


        TextView status = (TextView)findViewById(R.id.status);

        byte buf[] = new byte[2048];
        byte toSend[] = new byte[3];
        toSend[0] = 5;
        toSend[1] = 10;
        toSend[2] = 15;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        DatagramPacket send =  new DatagramPacket(toSend, toSend.length);
        try {
            InetAddress ia = InetAddress.getLocalHost();
            send.setAddress(ia);
            send.setPort(1199);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            datagramSocket = new DatagramSocket(1199);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        Button listenButton = (Button)findViewById(R.id.listenButton);
        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    datagramSocket.receive(datagramPacket);
                    status.setText(new String(packet.getData(), packet.getOffset(), packet.getLength()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
        });


        Button connectButton = (Button)findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    datagramSocket.send(send);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });






    }
}