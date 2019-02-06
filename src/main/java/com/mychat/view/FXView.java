package com.mychat.view;

import com.mychat.client.ClientChat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FXView implements View {

    @FXML
    public TextField message;
    @FXML
    public TextArea chat;
    @FXML
    public Button sendButton;
    @FXML
    public TextField nicknameField;
    @FXML
    public Button connectToServerButton;

    private ClientChat clientChat;


    public void write(String message, int roomId) {

        System.out.println(message);
        clientChat.getStringBuilders().get(roomId).append(message + System.lineSeparator());
        if (clientChat.getCurrentChannelNumber() == roomId) {
            chat.appendText(message + System.lineSeparator());
        }
    }

    public void errorMessage(String message) {
        connectToServerButton.setText(message);
    }

    @FXML
    public void typedEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendButtonActionPerformed();
        }
    }

    @FXML
    public void sendButtonActionPerformed() {
        clientChat.writeToServer(nicknameField.getText() + " : " + message.getText());
        message.setText("");
    }

    @FXML
    public void connectToServer() {
        if (nicknameField.getText().equals("")) {
            nicknameField.setPromptText("PROVIDE NICKNAME FIRST");
        } else {
            ClientChat clientChat = new ClientChat("localhost", 8000, this);
            try {
                clientChat.startClient();
                this.clientChat = clientChat;
                connectToServerButton.setText("Connected");
                connectToServerButton.setDisable(true);
            } catch (IOException e) {
                errorMessage("Cannot connect to Server");
            }
        }
    }

    public void channelOneClicked() {
        clientChat.setCurrentChannelNumber(0);
        chat.setText(clientChat.getStringBuilders().get(0).toString());
    }

    public void channelTwoClicked() {
        clientChat.setCurrentChannelNumber(1);
        chat.setText(clientChat.getStringBuilders().get(1).toString());
    }

    public void channelThreeClicked() {
        clientChat.setCurrentChannelNumber(2);
        chat.setText(clientChat.getStringBuilders().get(2).toString());
    }
}
