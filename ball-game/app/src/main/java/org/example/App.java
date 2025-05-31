package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ball game");
        primaryStage.setMaximized(true);

        Ball ball = new Ball();
        ball.main(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
