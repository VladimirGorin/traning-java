package org.example;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Ball {
    private final double RADIUS = 30;
    private final double STEP = 20;
    private final Color COLOR = Color.web("#FF5733");

    public void main(Stage primaryStage) {
        init(primaryStage);

    }

    public void init(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        Circle ball = new Circle(RADIUS, COLOR);

        ball.setCenterX(screenWidth / 2);
        ball.setCenterY(screenHeight / 2);

        Pane root = new Pane(ball);
        Scene scene = new Scene(root, screenWidth, screenHeight);

        keyHandler(scene, ball);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void keyHandler(Scene scene, Circle ball) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            Double ballXPosition = ball.getCenterX();
            Double ballYPosition = ball.getCenterY();

            switch (event.getCode().toString().toLowerCase()) {
                case "w" -> {
                    System.out.println("w" + ballYPosition);
                    ball.setCenterY(ballYPosition - this.STEP);
                }
                case "a" -> {
                    System.out.println("a");
                    ball.setCenterX(ballXPosition - this.STEP);
                }
                case "s" -> {
                    System.out.println("s");
                    ball.setCenterY(ballYPosition + this.STEP);
                }
                case "d" -> {
                    System.out.println("d");
                    ball.setCenterX(ballXPosition + this.STEP);
                }
                default -> throw new AssertionError();
            }
        });
    }

}
