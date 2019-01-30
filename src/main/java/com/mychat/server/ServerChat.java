package com.mychat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerChat {
    private ArrayList<PrintWriter> clientsPrintWriters = new ArrayList<PrintWriter>();
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

    public ArrayList<PrintWriter> getClientsPrintWriters() {
        return clientsPrintWriters;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void serverListen() {
        try {
            while (true) {


                Socket socket = serverSocket.accept();
                System.out.println("ACCEPTED SOCKET");
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                clientsPrintWriters.add(printWriter);
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

        public MessagesReceiver(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void run() {
            String message;
            try {
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message + " Message from client");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
