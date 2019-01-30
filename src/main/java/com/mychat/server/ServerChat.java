package com.mychat.server;

import com.mychat.client.ClientChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ServerChat {
    private ArrayList<ClientChat> clients = new ArrayList<ClientChat>();
    private final int port;
    private ServerSocket serverSocket;
    public ServerChat(int port) {
        this.port = port;
    }

    public void startServer(){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ClientChat> getClients() {
        return clients;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
}
