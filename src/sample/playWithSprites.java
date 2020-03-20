package sample;
import sample.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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



public class playWithSprites extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("playWithSprites");

        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
        pointVector mouse = new pointVector(0,0);
        Canvas canvas = new Canvas(500, 500);
        root.getChildren().add(canvas);
        theScene.addEventFilter(MouseEvent.ANY, e-> {
            mouse.set(e.getX(), e.getY());
        });
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Sprite earth = new Sprite("earth.png");
        Sprite sun = new Sprite("sun.png");
        pointVector sunP = new pointVector(198, 198);
        sun.setPos(sunP.x,sunP.y);
        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.clearRect(0, 0, 500, 500);
                earth.setPos(mouse.x -16,mouse.y-16);
                earth.render(gc);
                if(sun.intersects(earth)){
                    System.out.println("true");
                    sunP.x=Math.random()*450+1;
                    sunP.y=Math.random()*450+1;
                }
                sun.setPos(sunP.x,sunP.y);
                sun.render(gc);
            }

        }.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
