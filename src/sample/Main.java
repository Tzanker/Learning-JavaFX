package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Canvas Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);

        Canvas canvas = new Canvas(500, 500);
        root.getChildren().add(canvas);
        ArrayList<Integer> input = new ArrayList<Integer>();
        input.add(1);
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (code == "SPACE") {
                            input.set(0,input.get(0)*-1);
                        }
                    }
                }
        );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image earth = new Image(getClass().getResourceAsStream("earth.png"));
        Image sun = new Image(getClass().getResourceAsStream("sun.png"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = 232 + 128 * Math.cos(t)*input.get(0);
                double y = 232 + 128 * Math.sin(t*input.get(0));
                gc.clearRect(0, 0, 500, 500);
                gc.drawImage(earth, x, y);
                gc.drawImage(sun, 196, 196);
            }

        }.start();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
