package tetris;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Objects;

public class Driver extends Application {
    // We lock the FPS of the game at 60FPS.
    private static final int FPS_COUNT = 60;
    private static final Timeline gameLoop = new Timeline();
    //Tetris-game object
    static TetrisGame tetrisGame = new TetrisGame();

    //Variables for drawing.
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 400;
    private static final int GRID_CELL_WIDTH = CANVAS_WIDTH/10;
    private static final int GRID_CELL_HEIGHT = CANVAS_HEIGHT/20;
    private static final Canvas gameWindow = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);



    /*
        Game Window width is 500, 1000
        Features :
            Pause/Play
            Drop using space
            Level advancing


     */

    public void buildGameLoop(){
        Duration frameDur = Duration.millis(1000.0/FPS_COUNT);

        KeyFrame oneFrame = new KeyFrame(frameDur, new EventHandler<ActionEvent>() {
            int i = 0;
            @Override
            public void handle(ActionEvent actionEvent) {

                if(i==0){
                    if(tetrisGame.getFallingPiece() != null){
                        erasePiece(gameWindow.getGraphicsContext2D());
                    }
                    tetrisGame.tickGame();
                    if(tetrisGame.clearedRows.size() > 0){
                        eraseRow(gameWindow.getGraphicsContext2D());
                    }



                }

                displayPiece(gameWindow.getGraphicsContext2D());
                displayBoard(gameWindow.getGraphicsContext2D());




                i = (i + 1) % tetrisGame.getGameSpeedVals().get(tetrisGame.getCurrentLevel());

            }
        });
        //Timeline object for game control
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(oneFrame);


    }

    private void displayBoard(GraphicsContext gc) {

        for(int i = 0; i < 20; i++){
            for(int j =0; j < 10; j++){
                if(tetrisGame.getBoard().get(i).get(j) != null ){
                    gc.setFill(tetrisGame.getBoard().get(i).get(j));
                    gc.setStroke(Color.BLACK);
                    gc.fillRect((j)*GRID_CELL_WIDTH, (i)*GRID_CELL_HEIGHT,
                            GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
                }
            }
        }

    }

    private void erasePiece(GraphicsContext gc) {
        Tetrominoes piece = tetrisGame.getFallingPiece();
        for(int i=0;i<=3;i++) {
            int xOffset = piece.pieceXLoc.get(i);
            int yOffset = piece.pieceYLoc.get(i);
            gc.setFill(Color.BLACK);
            gc.fillRect((xOffset)*GRID_CELL_WIDTH, (yOffset)*GRID_CELL_HEIGHT,
                    GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
        }

    }
    private void eraseRow(GraphicsContext gc){
//        for(int i =0; i<tetrisGame.clearedRows.size(); i++){
//            for(int j = 0; j < 10; j++){
//                gc.setFill(Color.BLACK);
//                gc.fillRect((j)*GRID_CELL_WIDTH, (tetrisGame.clearedRows.get(i))*GRID_CELL_HEIGHT,
//                        GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
//
//            }
//        }
        for(int i = 0; i < 20; i++){
            for(int j =0; j < 10; j++){

                    gc.setFill(Color.BLACK);
                    gc.setStroke(Color.BLACK);
                    gc.fillRect((j)*GRID_CELL_WIDTH, (i)*GRID_CELL_HEIGHT,
                            GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
            }
        }
    }
    private void displayPiece(GraphicsContext gc){
        Tetrominoes piece = tetrisGame.getFallingPiece();
        gc.setFill(piece.getColor());
        gc.setStroke(Color.WHITE);

        for(int i=0;i<=3;i++){
            int xOffset = piece.pieceXLoc.get(i);
            int yOffset = piece.pieceYLoc.get(i);
            gc.fillRect((xOffset)*GRID_CELL_WIDTH, (yOffset)*GRID_CELL_HEIGHT,
                    GRID_CELL_WIDTH, GRID_CELL_HEIGHT);

        }


    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        stage.setScene(scene);
        gameWindow.getGraphicsContext2D().fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());

        gameWindow.setLayoutX(0);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("canvas.css")).toExternalForm());
        /*Pane menuPane = new Pane();

        menuPane.setLayoutX(500);
        menuPane.setMinSize(200,1000);
        VBox menu = new VBox();
        menuPane.getChildren().add(menu);
        menuPane.getStyleClass().add("canvas");


        */
        root.getChildren().add(gameWindow);

        buildGameLoop();
        startGameLoop();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //System.out.println(keyEvent.getCode().toString());
                String s = keyEvent.getCode().toString();
                switch(s) {
                    case "LEFT":
                        if(tetrisGame.getFallingPiece() != null){
                            erasePiece(gameWindow.getGraphicsContext2D());
                        }
                        tetrisGame.translatePiece(true);
                        break;
                    case "RIGHT":
                        if(tetrisGame.getFallingPiece() != null){
                            erasePiece(gameWindow.getGraphicsContext2D());
                        }
                        tetrisGame.translatePiece(false);
                        break;
                    case "UP":

                        if(tetrisGame.getFallingPiece() != null){
                            erasePiece(gameWindow.getGraphicsContext2D());
                        }
                        tetrisGame.rotate();
                        break;
                }

            }
        });
        stage.show();
    }
    private void startGameLoop(){
        gameLoop.play();
    }
    // *************** GETTERS AND SETTERS **********************

    public static void main(String[] args) {
        launch(args);
    }
}
