package tetris;

import javafx.scene.paint.Color;

import java.util.*;

public class TetrisGame {
    private int currentLevel;
    private final HashMap<Integer, Integer> gameSpeedVals;
    private final HashMap<Integer, Integer> scoreVals;
    private Tetrominoes fallingPiece;
    private boolean isPiecePlaced;



    private int score;
    private int linesCleared;

    Queue<Tetrominoes> nextInLine;
    private final ArrayList<ArrayList<Color>> board;
    ArrayList<Integer> clearedRows;

    public TetrisGame(){
        this.currentLevel = 5;
        this.score = 0;
        this.linesCleared = 0;
        ArrayList<ArrayList<Color>> board = new ArrayList<>(20);
        for(int i = 1; i<=20; i++){
            ArrayList<Color> x = generateRow();
            board.add(x);
        }
        this.board = board;
        this.clearedRows = new ArrayList<>();
        nextInLine = new LinkedList<>();
        scoreVals = new HashMap<>();
        scoreVals.put(1, 40);
        scoreVals.put(2, 100);
        scoreVals.put(3, 300);
        scoreVals.put(4, 1200);

        gameSpeedVals = new HashMap<>();
        gameSpeedVals.put(0,48);
        gameSpeedVals.put(1,43);
        gameSpeedVals.put(2,38);
        gameSpeedVals.put(3,33);
        gameSpeedVals.put(4,28);
        gameSpeedVals.put(5,23);
        gameSpeedVals.put(6,11);
        this.isPiecePlaced = true;
        fillQueue();

    }
    private ArrayList<Color> generateRow(){
        ArrayList<Color> x = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            x.add(null);
        }
        return x;
    }
    private void fillQueue() {
        Random rand = new Random();
        if (nextInLine.isEmpty()){
            nextInLine.add(Tetrominoes.generatePiece(rand.nextInt(6)));
            nextInLine.add(Tetrominoes.generatePiece(rand.nextInt(6)));
            nextInLine.add(Tetrominoes.generatePiece(rand.nextInt(6)));

        }else{
            nextInLine.add(Tetrominoes.generatePiece(rand.nextInt(7)));
        }

    }

    public int getCurrentLevel() {
        return currentLevel;
    }


    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public HashMap<Integer, Integer> getGameSpeedVals() {
        return gameSpeedVals;
    }


    public void tickGame() {
        if(isPiecePlaced){
            if(clearedRows.size() > 0){
                clearedRows.clear();
            }
            fallingPiece = nextInLine.poll();
            fillQueue();
            isPiecePlaced = false;
        }
        assert fallingPiece != null;
        if(checkForMove()){
            for(int i = 0; i <= 3; i++){
                int current = fallingPiece.pieceYLoc.get(i);
                fallingPiece.pieceYLoc.set(i, current + 1);

            }
        }else{
            placePiece();
            checkWin();
            isPiecePlaced = true;
        }


    }

    private void placePiece() {
        ArrayList<Integer> pieceY = fallingPiece.pieceYLoc;
        ArrayList<Integer> pieceX = fallingPiece.pieceXLoc;
        for(int i = 0; i<=3; i++){
            board.get(pieceY.get(i)).set(pieceX.get(i), fallingPiece.color );
        }


    }

    private boolean checkForMove() {
        ArrayList<Integer> pieceY = fallingPiece.pieceYLoc;
        ArrayList<Integer> pieceX = fallingPiece.pieceXLoc;

        for(int i = 0 ; i<=3; i++){
            if(pieceY.get(i) == 19){
                return false;
            }
            if(board.get(pieceY.get(i)+1).get(pieceX.get(i)) != null){
                return false;
            }
        }
        return true;
    }
    public void resetRow(){
        int j = 0;
        for(Integer i : clearedRows){
            board.remove(i + j);
            board.add(0, generateRow());
            j++;

        }
    }
    public void checkWin() {
        boolean cleared = true;
        for(int i = 19; i >= 0; i--){
            for(int j = 0; j < 10; j++) {
                if(board.get(i).get(j) == null){
                    cleared = false;
                    break;
                }
            }
            if(cleared){
                clearedRows.add(i);
            }
            cleared = true;

        }
        if(clearedRows.size() > 0){
            increaseScore();
            resetRow();
        }

        

    }

    private void increaseScore() {
        linesCleared += clearedRows.size();
        score += scoreVals.get(clearedRows.size())*(currentLevel+1);
        if(linesCleared / 10 > currentLevel+1){
            currentLevel = currentLevel + 1;
        }

    }

    public void translatePiece(boolean direction) {
        ArrayList<Integer> pieceX = fallingPiece.pieceXLoc;
        ArrayList<Integer> pieceY = fallingPiece.pieceYLoc;
        if(direction){

            if(checkDirection(true, pieceX, pieceY)){
                for(int i=0; i<=3; i++){
                    pieceX.set(i, pieceX.get(i)-1);
                }
            }
        }else{
            if(checkDirection(false, pieceX, pieceY)){

                for (int i=0; i<=3; i++){
                    pieceX.set(i, pieceX.get(i)+1);
                }
            }

        }


    }
    // Add check for adjacent pieces
    private boolean checkDirection(boolean direction, ArrayList<Integer> pieceX, ArrayList<Integer> pieceY) {

        if(direction){
            for(int i = 0; i<= 3; i++){

                if(pieceX.get(i)-1 >= 0 ){

                    if (board.get(pieceY.get(i)).get(pieceX.get(i)-1) != null){
                        System.out.println(pieceY.get(i));
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }else{
            for(int i = 0; i<= 3; i++){

                if(pieceX.get(i)+1 <= 9 ){

                    if (board.get(pieceY.get(i)).get(pieceX.get(i)+1) != null){
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Color>> getBoard() { return board; }


    public Tetrominoes getFallingPiece() {
        return fallingPiece;
    }



    public void rotate() {

        fallingPiece.rotate();
    }
    public int getScore() {
        return score;
    }


}
