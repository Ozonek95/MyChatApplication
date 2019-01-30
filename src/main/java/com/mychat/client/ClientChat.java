package com.mychat.client;

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


    public ClientChat(String ip, int port) {
        IP = ip;
        PORT = port;
    }

    public void startClient(){
        try {
            socket = new Socket(IP,PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

            Thread thread = new Thread(new MessageReceiver());
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToServer(String  message){
        printWriter.println("Hello from client "+message);
        printWriter.flush();
    }

    class MessageReceiver implements Runnable{

        public void run() {
            String message;
            try {

                while ((message=bufferedReader.readLine())!=null){
                    System.out.println(message);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }
}
