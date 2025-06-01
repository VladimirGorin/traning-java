package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Group group = new Group();

    Color sceneColor = Color.web("#000000");
    Color ballColor = Color.web("#FF5733");
    Color playerEntityColor = Color.web("#FF5733");

    double ballRadius = 30;
    double ballDurationX = 7;
    double ballDurationY = 7;

    Circle ball = null;
    Scene scene = null;

    Rectangle playerEntity = null;
    double playerEntityWidth = 40;
    double playerEntityHeight = 400;
    double playerEntityStep = 60;

    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ball game");
        primaryStage.setMaximized(true);

        ball();
        player();

        scene = new Scene(group, screenWidth, screenHeight, sceneColor);
        primaryStage.setScene(scene);

        playerControllers();
        ballAnimation();

        primaryStage.show();
    }

    public void player() {
        Rectangle rectangle = new Rectangle(playerEntityWidth, playerEntityHeight);
        rectangle.setFill(playerEntityColor);

        playerEntity = rectangle;

        group.getChildren().add(rectangle);
    }

    public void playerControllers() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            double pex = playerEntity.getX();
            double pey = playerEntity.getY();

            String pressedKey = event.getCode().toString().toLowerCase();

            switch (pressedKey) {
                case "w" -> playerEntity.setY(pey - playerEntityStep);
                case "a" -> playerEntity.setX(pex - playerEntityStep);
                case "s" -> playerEntity.setY(pey + playerEntityStep);
                case "d" -> playerEntity.setX(pex + playerEntityStep);
                default -> System.out.println("default pressed key: " + pressedKey);
            }
        });
    }

    public void ball() {
        ball = new Circle(ballRadius, ballColor);
        ball.setCenterX(screenWidth / 2);
        ball.setCenterY(screenHeight / 2);

        group.getChildren().add(ball);
    }

    public void ballAnimation() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double br = ball.getRadius();
                double bx = ball.getCenterX();
                double by = ball.getCenterY();

                if (bx - br <= 0 || bx + br >= scene.getWidth()) {
                    ballDurationX *= -1;
                }

                if (by - br <= 0 || by + br >= scene.getHeight()) {
                    ballDurationY *= -1;
                }

                if (ball.getBoundsInParent().intersects(playerEntity.getBoundsInParent())) {
                    ballDurationX *= -1;
                    ballDurationY *= -1;

                    while (ball.getBoundsInParent().intersects(playerEntity.getBoundsInParent())) {
                        ball.setCenterX(ball.getCenterX() + ballDurationX);
                        ball.setCenterY(ball.getCenterY() + ballDurationY);
                    }
                }

                ball.setCenterX(bx + ballDurationX);
                ball.setCenterY(by + ballDurationY);
            }
        }.start();
    }
}
