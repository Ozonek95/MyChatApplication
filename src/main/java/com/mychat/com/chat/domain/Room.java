package com.mychat.com.chat.domain;

import java.net.Socket;
import java.util.List;

public class Room {
    public Room(int id) {
        this.id = id;
    }

    private int id;
    private List<Socket> usersInRoom;

    public void addUser(Socket socket){
        usersInRoom.add(socket);
    }

    public void deleteUser(Socket socket){
        if(usersInRoom.contains(socket)){
            usersInRoom.remove(socket);
        }
    }

    public List<Socket> getUsersInRoom() {
        return usersInRoom;
    }
}
