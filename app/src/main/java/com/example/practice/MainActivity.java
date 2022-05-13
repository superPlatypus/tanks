package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zerokol.views.joystickView.JoystickView;
import com.zerokol.views.joystickView.JoystickView.OnJoystickMoveListener;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.xml.transform.sax.SAXResult;




public class MainActivity extends AppCompatActivity {

    protected static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    public static BufferedReader in; // поток чтения из сокета
    public static BufferedWriter out; // поток записи в сокет
    public static String word;
    private static WorkWithNetwork workWithNetwork;
    private static TextView status;

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


        EditText input = (EditText) findViewById(R.id.input);
        //TextView status = (TextView)findViewById(R.id.status);
        //String in = input.getText().toString();
        Button sendButton = (Button) findViewById(R.id.sendButton);

        //WorkWithNetwork workWithNetwork = new WorkWithNetwork(status);
        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // workWithNetwork.run();
                new ConnectTask().execute();
            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                word = input.getText().toString();
                new SendTask().execute();
            }
        });


    }

class ConnectTask extends AsyncTask<Void, Void, Void> {

    @Override
        protected Void doInBackground(Void... params) {
            TextView status = (TextView) findViewById(R.id.status);
            try {
                    clientSocket = new Socket("172.20.10.3", 4004); // этой строкой мы запрашиваем
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    status.setText("Ready!");
            } catch (IOException e) {
                System.err.println(e);
                status.setText(e.getMessage().toString());
            }
                return null;
            }
}

class SendTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
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
                        out.write(word + "\n");
                        out.flush();
                        String serverWord = in.readLine();
                        status.setText(serverWord);
                    //}
                }
                finally { // в любом случае необходимо закрыть сокет и потоки
                    //status.setText("Клиент был закрыт...");
//                    clientSocket.close();
//                    in.close();
//                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
                status.setText(e.getMessage().toString());
            }

            return null;

        }

}


}








