package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        Group root = new Group();
        Scene scene = new Scene(root, 700, 800);
        stage.setScene(scene);
        //stage.setResizable(false);

        Canvas gameWindow = new Canvas(500,800);
        gameWindow.setLayoutX(0);

        //Menu on the side 200*800
        Button button1 = new Button("Pause game");
        VBox menu = new VBox();
        menu.setLayoutX(500);
        menu.getChildren().add(button1);
        root.getChildren().addAll(gameWindow, menu);

        GraphicsContext gc = gameWindow.getGraphicsContext2D();

        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
