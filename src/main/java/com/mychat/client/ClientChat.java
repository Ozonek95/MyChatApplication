package com.mychat.client;

import com.mychat.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientChat {
    private final String IP;
    private final int PORT;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private View view;


    public ClientChat(String ip, int port, View view) {
        this.view=view;
        IP = ip;
        PORT = port;
    }

    public void startClient(){
        try {
            socket = new Socket(IP,PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            Thread thread = new Thread(new MessageReceiver(bufferedReader));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToServer(String  message){
        printWriter.println(message);
        printWriter.flush();
    }

    class MessageReceiver implements Runnable{

        BufferedReader bufferedReader;

        public MessageReceiver(BufferedReader bufferedReader) {
            this.bufferedReader = bufferedReader;
            System.out.println(bufferedReader);
        }

        public void run() {
            String message;
            try {

                while ((message=bufferedReader.readLine())!=null){
                    view.write(message);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
