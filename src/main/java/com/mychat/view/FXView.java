package com.mychat.view;

import com.mychat.client.ClientChat;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    @FXML
    public ListView usersList;

    private ClientChat clientChat;

    public void write(String message) {
        chat.appendText(message+System.lineSeparator());
    }

    @FXML
    public void typedEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendButtonActionPerformed();
        }
    }

    @FXML
    public void sendButtonActionPerformed() {
        clientChat.writeToServer(nicknameField.getText()+ " : " +message.getText());
        System.out.println(message.getText());
        message.setText("");
    }

    @FXML
    public void connectToServer() {
        if (nicknameField.getText().equals("")) {
             nicknameField.setPromptText("PROVIDE NICKNAME FIRST");
        }
        else {
            ClientChat clientChat = new ClientChat("localhost", 8000, this);
            clientChat.startClient();
            this.clientChat = clientChat;
            usersList.getItems().addAll("general");
            System.out.println("Connected");
            connectToServerButton.setText("Connected");
            connectToServerButton.setDisable(true);

        }
    }
}
