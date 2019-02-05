package com.mychat.view;

import com.mychat.client.ClientChat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class FXView implements View {


    public List<StringBuilder> texts = Arrays.asList(new StringBuilder(), new StringBuilder());
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

    private int actualRoom = 0;

    public void write(String message, int roomId) {

        System.out.println(message);
        texts.get(roomId).append(message+System.lineSeparator());
        if(actualRoom==roomId){
        chat.appendText(message + System.lineSeparator());
        }
    }

    @Override
    public int room() {
        return actualRoom;
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
            clientChat.startClient();
            this.clientChat = clientChat;
            connectToServerButton.setText("Connected");
            connectToServerButton.setDisable(true);
        }
    }

    public void channel1Click() {
        this.actualRoom = 0;
        chat.setText(texts.get(0).toString());
    }

    public void channel2Click() {
        this.actualRoom = 1;
        chat.setText(texts.get(1).toString());
    }
}
