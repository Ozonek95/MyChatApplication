package com.mychat.client;

import com.mychat.com.chat.domain.Channel;
import com.mychat.com.chat.domain.DrunkChannel;
import com.mychat.com.chat.domain.Message;
import com.mychat.com.chat.domain.NormalChannel;
import com.mychat.view.View;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ClientChat {
    private final String IP;
    private final int PORT;
    private Socket socket;
    private View view;
    private ObjectOutputStream outputStream;
    private int currentChannelNumber;
    private List<StringBuilder> stringBuilders = Arrays.asList(new StringBuilder(), new StringBuilder(), new StringBuilder());
    private List<Channel> channels = Arrays.asList(new NormalChannel(),new DrunkChannel());
    private Channel currentChannel = channels.get(0);


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
    public void startClient() throws IOException {

            socket = new Socket(IP,PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread thread = new Thread(new MessageReceiver(bufferedReader, inputStream));
            thread.start();

    }

    public void writeToServer(String  message){
        Message message1 = new Message(message,this.currentChannelNumber);
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
                    view.write(currentChannel.writeToStringBuilder(message.getMessage()),message.getRoomId());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public int getCurrentChannelNumber() {
        return currentChannelNumber;
    }

    public void setCurrentChannelNumber(int currentChannelNumber) {
        this.currentChannelNumber = currentChannelNumber;
        this.currentChannel = channels.get(currentChannelNumber);
    }

    public List<StringBuilder> getStringBuilders() {
        return stringBuilders;
    }
}
