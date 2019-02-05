package com.mychat.server;

import com.mychat.com.chat.domain.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerChat {
    private ArrayList<ObjectOutputStream> clientsOutputStreams = new ArrayList<>();
    private final int port;
    private ServerSocket serverSocket;

    public ServerChat(int port) {
        this.port = port;
    }

    public void startServer() {
        try {

            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void serverListen() {
        try {
            while (true) {


                Socket socket = serverSocket.accept();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                clientsOutputStreams.add(objectOutputStream);
                Thread thread = new Thread(new MessagesReceiver(socket));
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessagesReceiver implements Runnable {

        Socket clientSocket;
        BufferedReader bufferedReader;
        ObjectInputStream inputStream;

        public MessagesReceiver(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void run() {
            Message message;
            try {
                while ((message = (Message) inputStream.readObject()) != null) {
                    Iterator<ObjectOutputStream> iterator = clientsOutputStreams.iterator();
                    while (iterator.hasNext()) {
                        ObjectOutputStream clientChat = iterator.next();
                        clientChat.writeObject(message);
                        clientChat.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
