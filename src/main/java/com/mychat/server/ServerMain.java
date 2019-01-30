package com.mychat.server;


public class ServerMain {
    public static void main(String[] args) {
        ServerChat serverChat = new ServerChat(8000);
        serverChat.startServer();
        serverChat.serverListen();
    }
}
