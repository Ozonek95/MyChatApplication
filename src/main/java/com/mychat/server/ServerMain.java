package com.mychat.server;

import com.mychat.client.ClientChat;

public class ServerMain {
    public static void main(String[] args) {
        ServerChat serverChat = new ServerChat(8000);
        serverChat.startServer();
        serverChat.serverListen();
    }
}
