package com.mychat.client;

import com.mychat.com.chat.domain.Message;
import com.mychat.view.View;

import java.io.*;
import java.net.Socket;

public class ClientChat {
    private final String IP;
    private final int PORT;
    private Socket socket;
    private BufferedReader bufferedReader;
    private View view;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;


    public ClientChat(String ip, int port, View view) {
        this.view=view;
        IP = ip;
        PORT = port;
    }

    public void disconnect(){
        try {
            if(socket!=null)
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startClient(){
        try {
            socket = new Socket(IP,PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread thread = new Thread(new MessageReceiver(bufferedReader,inputStream));
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToServer(String  message){
        Message message1 = new Message(message, view.room());
        try {
            outputStream.writeObject(message1);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class MessageReceiver implements Runnable{

        ObjectInputStream inputStream;
        BufferedReader bufferedReader;

        public MessageReceiver(BufferedReader bufferedReader, ObjectInputStream inputStream) {
            this.inputStream = inputStream;
            this.bufferedReader = bufferedReader;
        }

        public void run() {
            Message message;
            try {

                while ((message=(Message) inputStream.readObject())!=null){
                    view.write(message.getMessage(),message.getRoomId());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
