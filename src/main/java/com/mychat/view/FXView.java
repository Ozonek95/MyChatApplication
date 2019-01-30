package com.mychat.view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXView implements View{

    public TextField message;
    public TextArea chat;
    public Button sendButton;
    public TextField nicknameField;
    public Button connectToServerButton;

    public void write(String message) {
        chat.appendText(nicknameField.getText()+":"+message+System.lineSeparator());
    }
}
