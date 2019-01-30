package com.mychat.client;

public class ClientMain {
    public static void main(String[] args) {
        ClientChat clientChat = new ClientChat("localhost",8000);
        clientChat.startClient();
        System.out.println("client started");
        clientChat.writeToServer("Hellooo");
    }
}
