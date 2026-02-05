package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load file FXML từ thư mục resources/com/example/
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Digital Shop");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void run() {
        launch();
    }
}