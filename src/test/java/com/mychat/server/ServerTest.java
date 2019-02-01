package com.mychat.server;


import com.mychat.client.ClientChat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ServerTest {
    ServerChat serverChat = new ServerChat(8000);
    @Before
    public void init(){
        serverChat.startServer();
    }

    @After
    public void closeServer(){
        try {
            serverChat.getServerSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartServer(){
        Assert.assertNotNull(serverChat.getServerSocket());
    }

    @Test
    public void testIfSocketIsCreatedOnCorrectPortNumber(){
        Assert.assertEquals(8000,serverChat.getServerSocket().getLocalPort());
    }

    @Test
    public void checkIfClientConnectToServer(){

//        ClientChat clientChat = new ClientChat("localhost",8000);
//        clientChat.startClient();
//        Assert.assertNotNull(clientChat.getSocket());
    }
}
