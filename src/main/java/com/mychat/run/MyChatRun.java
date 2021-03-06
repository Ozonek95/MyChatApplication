package com.mychat.run;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyChatRun extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("chat-app.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }
}
