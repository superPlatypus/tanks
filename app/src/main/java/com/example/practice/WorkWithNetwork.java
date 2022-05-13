package com.example.practice;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;



public class WorkWithNetwork extends Thread{
    private Socket clientSocket; //сокет для общения
    private BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private BufferedReader in; // поток чтения из сокета
    private  BufferedWriter out; // поток записи в сокет
    private  String word;
    private  WorkWithNetwork workWithNetwork;
    private TextView status;


//    public WorkWithNetwork(Socket clientSocket, BufferedReader reader, BufferedReader in,
//                                BufferedWriter out, String word, TextView status){
//        this.clientSocket = clientSocket;
//        this.reader = reader;
//        this.in = in;
//        this.out = out;
//        this.word = word;
//        this.status = status;
//
//    }

        public WorkWithNetwork(TextView status){

        this.status = status;
}

    @Override
    public void run(){
        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 4004); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                status.setText("Вы что-то хотели сказать? Введите это здесь:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение

                while (true) {

                    //String word = reader.readLine(); // ждём пока клиент что-нибудь
                    // не напишет в консоль
                    out.write(word + "\n"); // отправляем сообщение на сервер
                    out.flush();
                    String serverWord = in.readLine(); // ждём, что скажет сервер
                    status.setText(serverWord); // получив - выводим на экран
                }
            } finally { // в любом случае необходимо закрыть сокет и потоки
                status.setText("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
