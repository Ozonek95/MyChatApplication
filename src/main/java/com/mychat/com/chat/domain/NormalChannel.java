package com.mychat.com.chat.domain;

public class NormalChannel implements Channel {
    @Override
    public String writeToStringBuilder(String message) {
        return message;
    }
}
