package com.mychat.com.chat.domain;

import com.mychat.client.ClientChat;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private int roomId;

    public Message(String message, int roomId) {
        this.message = message;
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public int getRoomId() {
        return roomId;
    }
}
