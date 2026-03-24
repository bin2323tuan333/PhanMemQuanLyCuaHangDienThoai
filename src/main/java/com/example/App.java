package com.example;

import com.example.controllers.MainController;
import com.example.controllers.TopBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("MainContainer.fxml"));
        Scene scene = new Scene(mainLoader.load(), 1280, 720);

        stage.setTitle("Digital Shop");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }


    public static void run() {
        launch();
    }
}